package com.example.demo.Impl;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.entity.enums.AssetClassType;
import java.time.LocalDate;
import java.util.List;

public interface HoldingRecordService {
    HoldingRecord createHoldingRecord(Long userId, Long investorProfileId, AssetClassType assetClass, 
                                      String assetName, Integer quantity, Double pricePerUnit, LocalDate recordDate);
    List<HoldingRecord> getHoldingRecordsByInvestorProfile(Long investorProfileId);
    List<HoldingRecord> getHoldingRecordsByUser(Long userId);
    List<HoldingRecord> getHoldingRecordsByAssetClass(Long investorProfileId, AssetClassType assetClass);
    HoldingRecord updateHoldingRecord(Long id, Integer quantity, Double pricePerUnit);
    void deleteHoldingRecord(Long id);
    Double calculateTotalPortfolioValue(Long investorProfileId);
    Double calculateAssetClassValue(Long investorProfileId, AssetClassType assetClass);
}