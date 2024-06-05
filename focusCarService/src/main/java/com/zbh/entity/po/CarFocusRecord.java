package com.zbh.entity.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Accessors(chain = true)
public class CarFocusRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer recordId;

    private String licensePlateNumber;

    private String plateColor;

    private String parkRange;


    private LocalDateTime recordTime;


    private LocalDateTime validityPeriodStart;


    private LocalDateTime validityPeriodEnd;

    private Integer pushMessage;

    private String pusherName;

    private String pusherPhone;


}
