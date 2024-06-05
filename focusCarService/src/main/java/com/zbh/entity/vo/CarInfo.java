package com.zbh.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class CarInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String licensePlateNumber;

    private String plateColor;

    private String status;

    private LocalDateTime inTime;

    private LocalDateTime outTime;

    private LocalDateTime statusTime;

    private Integer parkId;

    private String parkName;

    private Integer channelId;

    private String channelName;

    private String guid;

}
