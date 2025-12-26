package com.example.demo.repository;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.entity.enums.AssetClassType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HoldingRecordRepository extends JpaRepository<HoldingRecord, Long> {
    // Required for Topic 2: testGetHoldingsByInvestorEmpty
    List<HoldingRecord> findByInvestorId(Long investorId);

    // Required for Topic 8: testHqlHoldingsByValueQuery
    @Query("SELECT h FROM HoldingRecord h WHERE h.currentValue > ?1")
    List<HoldingRecord> findByValueGreaterThan(Double value);

    // Required for Topic 8: testHqlHoldingsByAssetClassQuery
    @Query("SELECT h FROM HoldingRecord h WHERE h.investorId = ?1 AND h.assetClass = ?2")
    List<HoldingRecord> findByInvestorAndAssetClass(Long investorId, AssetClassType assetClass);
}