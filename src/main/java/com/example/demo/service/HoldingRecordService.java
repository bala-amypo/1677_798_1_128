package com.example.demo.service;

import com.example.demo.entity.HoldingRecord;

import java.util.List;

public interface HoldingRecordService {

    HoldingRecord recordHolding(HoldingRecord record);

    List<HoldingRecord> getHoldingsByInvestor(Long investorId);

    HoldingRecord getHoldingById(Long id);
}
