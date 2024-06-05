package com.zbh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zbh.common.Resp;
import com.zbh.common.RespConstants;
import com.zbh.config.MyException;
import com.zbh.dao.CarFocusRecordDao;
import com.zbh.entity.po.CarFocusRecord;
import com.zbh.service.IUpholdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class UpholdServiceImpl implements IUpholdService {

    @Resource
    private CarFocusRecordDao carFocusRecordMapper;

    @Override
    public Resp insertFocusCar(CarFocusRecord record) {
        try {
            int isSuccess = carFocusRecordMapper.insertFocusCar(record);
            if(isSuccess == 0) {
                return Resp.errorResp("0002", "重点车辆添加失败", "");
            }
            return Resp.successResp("0001", "重点车辆添加成功", "");
        } catch (Exception e) {
            throw new MyException(RespConstants.INSERT_FOCUS_ERROR);
        }
    }

    @Override
    public Resp deleteByLicensePlateNumber(String licensePlateNumber) {
        try {
            int isSuccess = carFocusRecordMapper.deleteByLicensePlateNumber(licensePlateNumber);
            if(isSuccess == 0) {
                return Resp.errorResp("0002", "重点车辆删除失败", licensePlateNumber);
            }
            return Resp.successResp("0001", "重点车辆删除成功", licensePlateNumber);
        } catch (Exception e) {
            throw new MyException(RespConstants.DELETE_FOCUS_ERROR);
        }
    }

    @Override
    public Resp updateFocusCar(CarFocusRecord record) {
        try {
            int isSuccess = carFocusRecordMapper.updateFocusCar(record);
            if (isSuccess == 0) {
                return Resp.errorResp("0", "重点车辆更新失败", record);
            }
            return Resp.successResp("1", "重点车辆更新成功", record);
        } catch (Exception e) {
            throw new MyException(RespConstants.UPDATE_FOCUS_ERROR);
        }

    }

    @Override
    public Resp selectByMultipleConditional(Integer parkId, String licensePlateNumber, Integer pageNum, Integer pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<CarFocusRecord> carFocusRecords = carFocusRecordMapper.selectByMultipleConditional(parkId.toString(), licensePlateNumber);
            PageInfo<CarFocusRecord> pageInfo = new PageInfo<>(carFocusRecords);
            List<CarFocusRecord> carFocusList = pageInfo.getList();
            if (carFocusRecords.isEmpty()) {
                return Resp.successResp("0", "无重点车辆", carFocusList);
            }
            log.info("重点车辆:{}", carFocusList);
            return Resp.successResp("1", "重点车辆查询成功", carFocusList, pageInfo.getTotal(), pageInfo.getPageNum());
        } catch (Exception e){
            throw new MyException(RespConstants.SELECT_FOCUS_ERROR);
        }
    }



}
