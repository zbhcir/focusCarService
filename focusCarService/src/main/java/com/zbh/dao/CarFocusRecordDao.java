package com.zbh.dao;

import com.zbh.entity.po.CarFocusRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarFocusRecordDao {

    int insertFocusCar(CarFocusRecord record);

    int deleteByLicensePlateNumber(String licensePlateNumber);

    int updateFocusCar(CarFocusRecord record);

    List<CarFocusRecord> selectByMultipleConditional(String parkId, String licensePlateNumber);

    CarFocusRecord selectByLicensePlateNumber(String licensePlateNumber);
}
