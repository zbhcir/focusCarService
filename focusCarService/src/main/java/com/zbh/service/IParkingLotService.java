package com.zbh.service;

import com.zbh.common.Resp;
import com.zbh.entity.dto.ParkEnterRecord;
import com.zbh.entity.dto.ParkExitRecord;
import com.zbh.entity.dto.RecordPair;

import java.util.List;

public interface IParkingLotService {

    Resp sendEnterRecord(ParkEnterRecord record);

    Resp sendExitRecord(ParkExitRecord record);

}
