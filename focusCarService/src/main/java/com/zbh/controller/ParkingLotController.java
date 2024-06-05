package com.zbh.controller;

import com.zbh.common.Resp;
import com.zbh.entity.dto.ParkEnterRecord;
import com.zbh.entity.dto.ParkExitRecord;
import com.zbh.entity.dto.RecordPair;
import com.zbh.service.IParkingLotService;
import com.zbh.utils.ParkingUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/parkingLot")
public class ParkingLotController {

    @Resource
    private IParkingLotService parkingLotService;

    /**
     * @description: 生成num条停车场进场和出场记录，并发送到云平台
     * @param num 生成记录数量
     * @return
     */
    @RequestMapping(value = "/sendCarInfo", method = RequestMethod.POST)
    public Resp generateAndSendCarInfo(@RequestParam Integer num) {
        // 生成num条停车场进场和出场记录
        RecordPair recordPair = ParkingUtils.generateRecord(num);
        List<ParkEnterRecord> enterRecords = recordPair.getEnterRecords();
        List<ParkExitRecord> exitRecords = recordPair.getExitRecords();
        // 发送停车场进场和出场记录
        for (ParkEnterRecord enterRecord : enterRecords) {
            log.info("停车场车辆进场:" + enterRecord);
            parkingLotService.sendEnterRecord(enterRecord);
        }
        for (ParkExitRecord exitRecord : exitRecords) {
            log.info("停车场车辆出场:" + exitRecord);
            parkingLotService.sendExitRecord(exitRecord);
        }
        return Resp.successResp("1", "生成停车场记录并发送云平台成功", recordPair);
    }




}
