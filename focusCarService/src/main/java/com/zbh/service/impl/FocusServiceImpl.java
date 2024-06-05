package com.zbh.service.impl;

import com.alibaba.fastjson.JSON;
import com.zbh.common.Resp;
import com.zbh.common.RespConstants;
import com.zbh.config.MyException;
import com.zbh.dao.CarFocusRecordDao;
import com.zbh.entity.po.CarFocusRecord;
import com.zbh.entity.vo.CarInfo;
import com.zbh.service.IFocusService;
import com.zbh.service.ITrackService;
import com.zbh.utils.DingTalkUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class FocusServiceImpl implements IFocusService {

    @Autowired
    private CarFocusRecordDao carFocusRecordDao;

    @Autowired
    private ITrackService trackService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public Resp handleValidFocusCar(CarInfo carInfo) {
        // 判断车辆是否为有效期内重点车辆
        CarFocusRecord focusRecord = isValidityFocusCar(carInfo);
        if (focusRecord != null) {
            // 是，向钉钉发送通知
            int isSend = sendToDing(carInfo, focusRecord);
            // 插入重点车辆轨迹表
            int isInsert = trackService.insertTrack(carInfo); // Assuming trackService is autowired
            // 判断是否发送和插入成功
            if (isSend == 1 && isInsert == 1) {
                return Resp.successResp("1", "有效重点车辆, 钉钉消息推送成功", carInfo);
            } else {
                return Resp.errorResp("0", "有效重点车辆, 钉钉消息推送失败", carInfo);
            }
        }
        return Resp.successResp("1","非有效期重点车辆，不进行推送", carInfo);
    }

    @Override
    public CarInfo processRecord(ConsumerRecord<String, String> record) {
        try {
            Optional<String> message = Optional.ofNullable(record.value());
            if (!message.isPresent()) {
                throw new MyException(RespConstants.INVALID_KAFKA_INFO);
            }
            return JSON.parseObject(message.get(), CarInfo.class);
        } catch (Exception e) {
            throw new MyException(RespConstants.INVALID_KAFKA_INFO);
        }
    }


    public CarFocusRecord isValidityFocusCar(CarInfo carInfo) {
        try {
            String licensePlateNumber = carInfo.getLicensePlateNumber();
            CarFocusRecord record = carFocusRecordDao.selectByLicensePlateNumber(licensePlateNumber);
            addStatusTime(carInfo);
            if (record == null || !isWithinValidityPeriod(record, carInfo)
                    || record.getPushMessage() == 0) {
                log.info("正常车辆：" + licensePlateNumber);
                return null;
            }
            log.info("有效期内重点车辆：" + licensePlateNumber);
            return record;
        } catch (Exception e) {
            throw new MyException(RespConstants.SELECT_FOCUS_ERROR);
        }
    }

    public int sendToDing(CarInfo carInfo, CarFocusRecord record) {
        try {
            if (diffInMinutes(carInfo) > 10){
                log.info("网络原因，消息延迟，不进行推送." + carInfo);
                return 0;
            }
            String rep = DingTalkUtils.sendToDing(carInfo, record);
            if (rep == null){
                return 0;
            }
            return 1;
        } catch (Exception e) {
            throw new MyException(RespConstants.SEND_DING_FILE);
        }

    }

    private boolean isWithinValidityPeriod(CarFocusRecord record, CarInfo carInfo) {
        LocalDateTime time = carInfo.getInTime() == null ? carInfo.getOutTime() : carInfo.getInTime();
        return time.isAfter(record.getValidityPeriodStart()) && time.isBefore(record.getValidityPeriodEnd());
    }

    private void addStatusTime(CarInfo carInfo){
        if(carInfo.getInTime() == null){
            carInfo.setStatus("出场");
            carInfo.setStatusTime(carInfo.getOutTime());
        } else {
            carInfo.setStatus("进场");
            carInfo.setStatusTime(carInfo.getInTime());
        }
    }

    private Long diffInMinutes(CarInfo carInfo){
        Duration duration = Duration.between(carInfo.getStatusTime(), LocalDateTime.now());
        return duration.toMinutes();
    }



}
