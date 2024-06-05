package com.zbh.entity.dto;

import com.zbh.entity.po.EnterRecord;
import com.zbh.entity.po.ExitRecord;
import lombok.Data;

import java.util.List;

@Data
public class RecordPair {
    private List<ParkEnterRecord> enterRecords;
    private List<ParkExitRecord> exitRecords;

    public RecordPair(List<ParkEnterRecord> enterRecords, List<ParkExitRecord> exitRecords) {
        this.enterRecords = enterRecords;
        this.exitRecords = exitRecords;
    }
}
