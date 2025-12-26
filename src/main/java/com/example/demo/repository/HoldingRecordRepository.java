package com.example.demo.repository;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.entity.enums.AssetClassType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HoldingRecordRepository extends JpaRepository<HoldingRecord, Long> {

    // Old name for compatibility with tests
    default List<HoldingRecord> findByValueGreaterThan(double value) {
        return findByCurrentValueGreaterThan(value);
    }

    // Old name for compatibility with tests
    default List<HoldingRecord> findByInvestorAndAssetClass(long investorId, AssetClassType assetClass) {
        return findByInvestorIdAndAssetClass(investorId, assetClass);
    }

    // Actual methods
    List<HoldingRecord> findByInvestorId(Long investorId);

    List<HoldingRecord> findByInvestorIdAndAssetClass(Long investorId, AssetClassType assetClass);

    List<HoldingRecord> findByCurrentValueGreaterThan(double value);

    Optional<HoldingRecord> findById(Long id);
}
