package com.example.demo.repository;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.entity.enums.AssetClassType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HoldingRecordRepository extends JpaRepository<HoldingRecord, Long> {

    List<HoldingRecord> findByValueGreaterThan(double value);

    Optional<HoldingRecord> findByInvestorAndAssetClass(long investorId, AssetClassType assetClass);
}
