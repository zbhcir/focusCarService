package com.zbh.controller;

import com.zbh.common.Resp;
import com.zbh.common.RespConstants;
import com.zbh.entity.po.CarFocusRecord;
import com.zbh.service.ITrackService;
import com.zbh.service.IUpholdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/platform")
public class PlatformController {
    @Resource
    private IUpholdService upholdService;
    @Resource
    private ITrackService trackService;


    /**
     * @description: 添加重点车辆
     * @param carFocusRecord 重点车辆信息
     */
    @RequestMapping(value = "/addFocusCar", method = RequestMethod.POST)
    public Resp addFocusCar(@RequestBody CarFocusRecord carFocusRecord) {
        return upholdService.insertFocusCar(carFocusRecord);
    }

    /**
     * @description: 通过车牌号删除重点车辆
     * @param licensePlateNumber 车牌号
     */
    @RequestMapping(value = "/deleteFocusCar", method = RequestMethod.POST)
    public Resp deleteFocusCar(@RequestParam String licensePlateNumber) {
        return upholdService.deleteByLicensePlateNumber(licensePlateNumber);
    }

    /**
     * @description: 修改重点车辆记录
     * @param carFocusRecord 重点车辆信息
     */
    @RequestMapping(value = "/updateFocusCar", method = RequestMethod.POST)
    public Resp updateFocusCar(@RequestBody CarFocusRecord carFocusRecord) {
        return upholdService.updateFocusCar(carFocusRecord);
    }

    /**
     * @description: 根据车场编号和模糊车牌号查询重点车辆
     * @param parkId 车场编号
     * @param licensePlateNumber 车牌号(模糊)
     */
    @RequestMapping(value = "/selectFocusCar", method = RequestMethod.POST)
    public Resp selectByParkIdOrPlateNumber(
            @RequestParam Integer parkId, @RequestParam String licensePlateNumber,
            @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return upholdService.selectByMultipleConditional(parkId, licensePlateNumber, pageNum, pageSize);
    }

    /**
     * @description: 查询重点车辆行驶轨迹
     * @param licensePlateNumber 车牌号
     * @param plateColor 车牌颜色
     * @param startTime 时间范围(开始)
     * @param endTime 时间范围(结束)
     * @return
     */
    @RequestMapping(value = "/selectTrack", method = RequestMethod.POST)
    public Resp selectTrack(
            @RequestParam String licensePlateNumber, @RequestParam String plateColor,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime,
            @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        if (startTime != null && endTime != null && endTime.isBefore(startTime)) {
            return Resp.errorResp(RespConstants.INVALID_TIME);
        }
        return trackService.selectTrack(licensePlateNumber, plateColor, startTime, endTime, pageNum, pageSize);
    }
}
