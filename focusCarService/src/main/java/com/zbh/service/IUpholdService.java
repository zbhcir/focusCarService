package com.zbh.service;

import com.zbh.common.Resp;
import com.zbh.entity.po.CarFocusRecord;

public interface IUpholdService {

    Resp insertFocusCar(CarFocusRecord record);

    Resp deleteByLicensePlateNumber(String licensePlateNumber);

    Resp updateFocusCar(CarFocusRecord record);

    Resp selectByMultipleConditional(Integer parkId, String licensePlateNumber, Integer pageNum, Integer pageSize);

}
