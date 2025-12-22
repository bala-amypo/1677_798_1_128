package com.example.demo.service;

import com.example.demo.entity.HoldingRecord;

import java.util.List;

public interface HoldingRecordService {

    HoldingRecord recordHolding(HoldingRecord holding);

    List<HoldingRecord> getHoldingsByInvestor(Long investorId);
    
    // Remove duplicate: List<HoldingRecord> findByInvestorId(Long investorId);

    HoldingRecord getHoldingById(Long id);

    List<HoldingRecord> getAllHoldings();
    
    // Optional: Add update and delete methods
    HoldingRecord updateHolding(Long id, HoldingRecord holding);
    
    void deleteHolding(Long id);
}