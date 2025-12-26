package com.example.demo.service;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.entity.enums.AssetClassType;

import java.util.List;

public interface HoldingRecordService {

    HoldingRecord recordHolding(HoldingRecord holdingRecord);

    List<HoldingRecord> getHoldingsByInvestor(Long investorId);

    HoldingRecord getHoldingById(Long id);

    List<HoldingRecord> getByInvestorAndAssetClass(Long investorId, AssetClassType assetClass);
}
