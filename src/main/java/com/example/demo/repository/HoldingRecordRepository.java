package com.example.demo.repository;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.entity.enums.AssetClassType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HoldingRecordRepository extends JpaRepository<HoldingRecord, Long> {

    // Find all holdings for a specific investor
    List<HoldingRecord> findByInvestorId(Long investorId);

    // Find a holding for a specific investor and asset class
    Optional<HoldingRecord> findByInvestorIdAndAssetClass(Long investorId, AssetClassType assetClass);

    // Additional methods you might need
    List<HoldingRecord> findByValueGreaterThan(double value);

    Optional<HoldingRecord> findByInvestorAndAssetClass(Long investorId, AssetClassType assetClass);
}
