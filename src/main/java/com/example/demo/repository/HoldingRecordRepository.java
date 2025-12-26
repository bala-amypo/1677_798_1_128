package com.example.demo.repository;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.entity.enums.AssetClassType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoldingRecordRepository extends JpaRepository<HoldingRecord, Long> {

    List<HoldingRecord> findByInvestorId(Long investorId);

    List<HoldingRecord> findByInvestorIdAndAssetClass(Long investorId, AssetClassType assetClass);

    // Method used by your test
    List<HoldingRecord> findByValueGreaterThan(double value);

    // Alias to match test method
    default List<HoldingRecord> findByInvestorAndAssetClass(long investorId, AssetClassType assetClass) {
        return findByInvestorIdAndAssetClass(investorId, assetClass);
    }
}
