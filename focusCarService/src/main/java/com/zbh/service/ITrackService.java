package com.zbh.service;

import com.zbh.common.Resp;
import com.zbh.entity.vo.CarInfo;

import java.time.LocalDateTime;

public interface ITrackService {

    Resp selectTrack(String licensePlateNumber, String plateColor, LocalDateTime startTime, LocalDateTime endTime, Integer pageNum, Integer pageSize);

    int insertTrack(CarInfo carInfo);
}
