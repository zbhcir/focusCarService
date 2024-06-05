package com.zbh.consumer;

import com.zbh.common.Resp;
import com.zbh.entity.vo.CarInfo;
import com.zbh.service.IFocusService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Service
public class FocusService {

    @Autowired
    private IFocusService focusService;

    @KafkaListener(topics = "open_record", groupId = "focus")
    public Resp listenEnterRecord(ConsumerRecord<String, String> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        CarInfo carInfo = focusService.processRecord(record);
        return focusService.handleValidFocusCar(carInfo);
    }

}
