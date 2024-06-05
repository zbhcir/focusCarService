package com.zbh.controller;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.zbh.common.Resp;
import com.zbh.entity.dto.ParkEnterRecord;
import com.zbh.entity.dto.ParkExitRecord;
import com.zbh.entity.po.EnterRecord;
import com.zbh.entity.po.ExitRecord;
import com.zbh.service.ICloudService;
import com.zbh.service.impl.CloudServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/open")
public class OpenController {

    @Autowired
    private ICloudService cloudService;

    @RequestMapping(value = "/enterRecord", method = RequestMethod.POST)
    public Resp sendEnterRecordKafka(@RequestBody ParkEnterRecord parkEnterrecord){
        return cloudService.sendEnterRecord(parkEnterrecord);
    }


    @RequestMapping(value = "/exitRecord", method = RequestMethod.GET)
    public Resp sendExitRecordKafka(@RequestBody ParkExitRecord parkExitRecord){
        return cloudService.sendExitRecord(parkExitRecord);
    }
}


















