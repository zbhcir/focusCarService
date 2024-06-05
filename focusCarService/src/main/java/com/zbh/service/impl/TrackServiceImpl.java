package com.zbh.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zbh.common.Resp;
import com.zbh.common.RespConstants;
import com.zbh.config.MyException;
import com.zbh.dao.TrackRecordDao;
import com.zbh.entity.po.TrackRecord;
import com.zbh.entity.vo.CarInfo;
import com.zbh.service.ITrackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class TrackServiceImpl implements ITrackService {

    @Resource
    private TrackRecordDao trackRecordDao;

    @Override
    public Resp selectTrack(String licensePlateNumber, String plateColor, LocalDateTime startTime, LocalDateTime endTime, Integer pageNum, Integer pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<TrackRecord> trackRecords = trackRecordDao.selectByConditional(licensePlateNumber, plateColor, startTime, endTime);
            PageInfo<TrackRecord> pageInfo = new PageInfo<>(trackRecords);
            List<TrackRecord> trackList = pageInfo.getList();
            if (trackList.isEmpty()) {
                return Resp.successResp("0001", "无时间范围内重点车辆行驶轨迹", trackList);
            }
            log.info("重点车辆轨迹:{}", trackList);
            return Resp.successResp("0001", "重点车辆行驶轨迹查询成功", trackList, pageInfo.getTotal(), pageInfo.getPageNum());
        } catch (Exception e) {
            throw new MyException(RespConstants.SELECT_TRACK_ERROR);
        }
    }

    @Override
    public int insertTrack(CarInfo carInfo) {
        try {
            TrackRecord trackRecord = BeanUtil.copyProperties(carInfo, TrackRecord.class);
            trackRecord.setStatusTime(carInfo.getStatusTime());
            return trackRecordDao.insertTrack(trackRecord);
        } catch (Exception e) {
            throw new MyException(RespConstants.INSERT_FOCUS_ERROR);
        }
    }

}
