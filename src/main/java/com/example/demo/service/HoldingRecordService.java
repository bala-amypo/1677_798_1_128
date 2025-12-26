// src/main/java/com/example/demo/service/HoldingRecordService.java
package com.example.demo.service;

import com.example.demo.entity.HoldingRecord;

import java.util.List;
import java.util.Optional;

public interface HoldingRecordService {
    HoldingRecord recordHolding(HoldingRecord record);
    List<HoldingRecord> getHoldingsByInvestor(Long investorId);
    Optional<HoldingRecord> getHoldingById(Long id);
}
