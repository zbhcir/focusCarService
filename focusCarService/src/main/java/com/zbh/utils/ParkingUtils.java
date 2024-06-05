package com.zbh.utils;

import com.zbh.config.ParkingLotConfig;
import com.zbh.entity.dto.ParkEnterRecord;
import com.zbh.entity.dto.ParkExitRecord;
import com.zbh.entity.dto.RecordPair;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ParkingUtils {

    public static RecordPair generateRecord(Integer num) {
        LocalDateTime time = LocalDateTime.now();
        List<ParkEnterRecord> enters = new ArrayList<>();
        List<ParkExitRecord> exist = new ArrayList<>();
        for(int i=0; i<num; i++){
            ParkEnterRecord parkEnterRecord = new ParkEnterRecord();
            String licensePlateNumber = "浙" + (char)(65 + generateRandNum(11)) + generateRandNum(10);
            time = time.plusSeconds(generateRandNum(10));
            LocalDateTime inTime = time;
            String plateColor = ParkingLotConfig.COLOR_MAP.get(generateRandNum(2));
            Integer parkId = generateRandNum(10) + 1000;
            String parkName = ParkingLotConfig.PARK_MAP.get(parkId);
            Integer channelId = generateRandNum(5);
            String channelName = ParkingLotConfig.CHANNEL_MAP.get(channelId);
            String guid = "" + parkId + "_" + UUID.randomUUID();
            parkEnterRecord.setLicensePlateNumber(licensePlateNumber);
            parkEnterRecord.setInTime(inTime);
            parkEnterRecord.setPlateColor(plateColor);
            parkEnterRecord.setParkId(parkId);
            parkEnterRecord.setParkName(parkName);
            parkEnterRecord.setChannelId(channelId);
            parkEnterRecord.setChannelName(channelName);
            parkEnterRecord.setGuid(guid);

            ParkExitRecord parkExitRecord = new ParkExitRecord();
            LocalDateTime outTime = inTime.plusSeconds(generateRandNum(10));
            channelId = generateRandNum(5);
            channelName = ParkingLotConfig.CHANNEL_MAP.get(channelId);
            parkExitRecord.setLicensePlateNumber(licensePlateNumber);
            parkExitRecord.setOutTime(outTime);
            parkExitRecord.setPlateColor(plateColor);
            parkExitRecord.setParkId(parkId);
            parkExitRecord.setParkName(parkName);
            parkExitRecord.setChannelId(channelId);
            parkExitRecord.setChannelName(channelName);
            parkExitRecord.setGuid(guid);
            enters.add(parkEnterRecord);
            exist.add(parkExitRecord);
        }
        return new RecordPair(enters, exist);
    }

    public static Integer generateRandNum(Integer max){
        return (int)(Math.random() * max+1);
    }
}
