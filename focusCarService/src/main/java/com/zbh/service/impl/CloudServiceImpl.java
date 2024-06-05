package com.zbh.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.zbh.common.Resp;
import com.zbh.common.RespConstants;
import com.zbh.config.MyException;
import com.zbh.entity.dto.ParkEnterRecord;
import com.zbh.entity.dto.ParkExitRecord;
import com.zbh.entity.po.EnterRecord;
import com.zbh.entity.po.ExitRecord;
import com.zbh.service.ICloudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
@Service
public class CloudServiceImpl implements ICloudService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final RestTemplate restTemplate = new RestTemplate();
    private final static String TOPIC_RECORD = "open_record";

    @Override
    public Resp sendEnterRecord(ParkEnterRecord parkEnterRecord) {
        try {
            EnterRecord enterRecord = BeanUtil.copyProperties(parkEnterRecord, EnterRecord.class);
            log.info("云平台解密并发送进场记录 主题:{} 进场记录:{}",TOPIC_RECORD, enterRecord);
            String jsonStr = JSON.toJSONString(enterRecord);
            kafkaTemplate.send(TOPIC_RECORD, jsonStr);
            return Resp.successResp("1", "云平台解密进场数据并发送成功", enterRecord);
        } catch (Exception e) {
            throw new MyException(RespConstants.CLOUD_SEND_FAIL);
        }
    }

    @Override
    public Resp sendExitRecord(ParkExitRecord parkExitRecord) {
        try {
            ExitRecord exitRecord = BeanUtil.copyProperties(parkExitRecord, ExitRecord.class);
            log.info("云平台解密并发送出场记录 主题:{} 出场记录:{}",TOPIC_RECORD, exitRecord);
            String jsonStr = JSON.toJSONString(exitRecord);
            kafkaTemplate.send(TOPIC_RECORD, jsonStr);
            return Resp.successResp("1", "云平台解密出场数据并发送成功", exitRecord);
        } catch (Exception e) {
            throw new MyException(RespConstants.CLOUD_SEND_FAIL);
        }
    }

}
