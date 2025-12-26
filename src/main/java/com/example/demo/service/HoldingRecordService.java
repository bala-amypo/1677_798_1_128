package com.example.demo.service;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.entity.enums.AssetClassType;

import java.util.List;

public interface HoldingRecordService {

    HoldingRecord getHoldingById(Long id);

    List<HoldingRecord> getHoldingsByInvestor(Long investorId);

    List<HoldingRecord> getByInvestorAndAssetClass(Long investorId, AssetClassType assetClass);

    HoldingRecord recordHolding(HoldingRecord holding);

    List<HoldingRecord> getHoldingsGreaterThan(double value);
}
