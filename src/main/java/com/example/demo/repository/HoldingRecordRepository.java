package com.example.demo.repository;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.entity.enums.AssetClassType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoldingRecordRepository extends JpaRepository<HoldingRecord, Long> {

    // Get all holdings for a specific investor
    List<HoldingRecord> findByInvestorId(Long investorId);

    // Get all holdings for a specific investor filtered by asset class
    List<HoldingRecord> findByInvestorIdAndAssetClass(Long investorId, AssetClassType assetClass);

    // Get all holdings with current value greater than a threshold
    List<HoldingRecord> findByCurrentValueGreaterThan(double value);
}
