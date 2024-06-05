package com.zbh.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.zbh.common.Resp;
import com.zbh.common.RespConstants;
import com.zbh.config.MyException;
import com.zbh.config.ParkingLotConfig;
import com.zbh.entity.dto.ParkEnterRecord;
import com.zbh.entity.dto.ParkExitRecord;
import com.zbh.entity.dto.RecordPair;
import com.zbh.entity.po.EnterRecord;
import com.zbh.entity.po.ExitRecord;
import com.zbh.service.IParkingLotService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ParkingLotServiceImpl implements IParkingLotService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String ENTER_RECORD_URL = "http://localhost:8081/cloudKafka/enterRecord";
    private static final String EXIT_RECORD_URL = "http://localhost:8081/cloudKafka/exitRecord";

    @Override
    public Resp sendEnterRecord(ParkEnterRecord record) {
        EnterRecord enterRecord = BeanUtil.copyProperties(record, EnterRecord.class);
        return sendRecord(enterRecord, ENTER_RECORD_URL);
    }

    @Override
    public Resp sendExitRecord(ParkExitRecord record) {
        ExitRecord exitRecord = BeanUtil.copyProperties(record, ExitRecord.class);
        return sendRecord(exitRecord, EXIT_RECORD_URL);
    }

    public Resp sendRecord(Object record, String url) {
        try {
            String jsonStr = JSON.toJSONString(record);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(jsonStr, headers);
            restTemplate.postForEntity(url, entity, String.class);
            return Resp.successResp("1", "停车场发送记录成功", jsonStr);
        } catch (Exception e){
            return Resp.errorResp(RespConstants.PARK_SEND_FAIL);
        }
    }







}
