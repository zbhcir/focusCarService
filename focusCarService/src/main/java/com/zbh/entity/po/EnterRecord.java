package com.zbh.entity.po;

import com.zbh.entity.vo.CarInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class EnterRecord extends CarInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDateTime inTime;

    private String licensePlateNumber;

    private String plateColor;

    private Integer parkId;

    private String parkName;

    private Integer channelId;

    private String channelName;

    private String guid;

}
