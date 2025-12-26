package com.example.demo.repository;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.entity.enums.AssetClassType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoldingRecordRepository extends JpaRepository<HoldingRecord, Long> {

    // Get all holdings for an investor
    List<HoldingRecord> findByInvestorId(Long investorId);

    // Get holdings for an investor filtered by asset class
    List<HoldingRecord> findByInvestorIdAndAssetClass(Long investorId, AssetClassType assetClass);

    // Get holdings greater than a certain value
    List<HoldingRecord> findByCurrentValueGreaterThan(double value);
}
