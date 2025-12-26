package com.example.demo.repository;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.entity.enums.AssetClassType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface HoldingRecordRepository extends JpaRepository<HoldingRecord, Long> {
    
    List<HoldingRecord> findByInvestorId(Long investorId);

    // Custom Query for Priority 64-65
    @Query("SELECT h FROM HoldingRecord h WHERE h.currentValue > ?1")
    List<HoldingRecord> findByValueGreaterThan(Double value);

    // Custom Query for Priority 66
    @Query("SELECT h FROM HoldingRecord h WHERE h.investorId = ?1 AND h.assetClass = ?2")
    List<HoldingRecord> findByInvestorAndAssetClass(Long investorId, AssetClassType assetClass);
}