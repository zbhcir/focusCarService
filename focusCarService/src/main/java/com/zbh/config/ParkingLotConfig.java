package com.zbh.config;

import java.util.HashMap;

public class ParkingLotConfig {

    public static final HashMap<Integer, String> COLOR_MAP = new HashMap<>();
    static {
        COLOR_MAP.put(1, "蓝");
        COLOR_MAP.put(2, "绿");
        COLOR_MAP.put(3, "黄");
//        COLOR_MAP.put(4, "白");
//        COLOR_MAP.put(5, "黑");
    }

    public static final HashMap<Integer, String> PARK_MAP = new HashMap<>();
    static {
        PARK_MAP.put(1001, "一号停车场");
        PARK_MAP.put(1002, "二号停车场");
        PARK_MAP.put(1003, "三号停车场");
        PARK_MAP.put(1004, "四号停车场");
        PARK_MAP.put(1005, "五号停车场");
        PARK_MAP.put(1006, "六号停车场");
        PARK_MAP.put(1007, "七号停车场");
        PARK_MAP.put(1008, "八号停车场");
        PARK_MAP.put(1009, "九号停车场");
        PARK_MAP.put(1010, "十号停车场");
    }

    public static final HashMap<Integer, String> CHANNEL_MAP = new HashMap<>();
    static {
        CHANNEL_MAP.put(1, "一号通道");
        CHANNEL_MAP.put(2, "二号通道");
        CHANNEL_MAP.put(3, "三号通道");
        CHANNEL_MAP.put(4, "四号通道");
        CHANNEL_MAP.put(5, "五号通道");
    }
}
