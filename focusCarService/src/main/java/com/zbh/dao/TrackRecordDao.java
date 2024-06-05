package com.zbh.dao;

import com.zbh.entity.po.TrackRecord;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface TrackRecordDao {

    int insertTrack(TrackRecord record);

    List<TrackRecord> selectByConditional(String licensePlateNumber, String plateColor, LocalDateTime startTime, LocalDateTime endTime);
//    List<TrackRecord> selectByConditional(String licensePlateNumber, String plateColor);
}
