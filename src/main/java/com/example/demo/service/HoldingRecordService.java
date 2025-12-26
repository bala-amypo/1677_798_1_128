package com.example.demo.service;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.entity.enums.AssetClassType;

import java.util.List;

public interface HoldingRecordService {

    // Get a single holding by its ID
    HoldingRecord getHoldingById(Long id);

    // Get all holdings for a specific investor
    List<HoldingRecord> getHoldingsByInvestor(Long investorId);

    // Get holdings for an investor filtered by asset class
    List<HoldingRecord> getByInvestorAndAssetClass(Long investorId, AssetClassType assetClass);

    // Save a new holding
    HoldingRecord recordHolding(HoldingRecord holding);

    // Get holdings with value greater than a threshold
    List<HoldingRecord> getHoldingsGreaterThan(double value);
}
