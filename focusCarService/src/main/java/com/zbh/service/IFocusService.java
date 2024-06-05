package com.zbh.service;

import com.zbh.common.Resp;
import com.zbh.entity.po.CarFocusRecord;
import com.zbh.entity.po.EnterRecord;
import com.zbh.entity.vo.CarInfo;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface IFocusService {

    Resp handleValidFocusCar(CarInfo carInfo);

    CarInfo processRecord(ConsumerRecord<String, String> record);

}
