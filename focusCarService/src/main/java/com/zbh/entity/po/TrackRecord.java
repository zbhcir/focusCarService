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
public class TrackRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer recordId;

    private String licensePlateNumber;

    private String plateColor;

    private String parkName;

    private Integer parkId;

    private LocalDateTime statusTime;

    private String status;


}
