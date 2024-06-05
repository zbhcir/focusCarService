package com.zbh.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ParkExitRecord {

    private Integer recordId;

    private LocalDateTime outTime;

    private String licensePlateNumber;

    private String plateColor;

    private Integer parkId;

    private String parkName;

    private Integer channelId;

    private String channelName;

    private String guid;
}
