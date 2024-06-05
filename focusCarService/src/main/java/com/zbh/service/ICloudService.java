package com.zbh.service;

import com.zbh.common.Resp;
import com.zbh.entity.dto.ParkEnterRecord;
import com.zbh.entity.dto.ParkExitRecord;
import com.zbh.entity.po.EnterRecord;
import com.zbh.entity.po.ExitRecord;

public interface ICloudService {

    Resp sendEnterRecord(ParkEnterRecord record);

    Resp sendExitRecord(ParkExitRecord record);


}
