package com.example.demo.repository;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.entity.enums.AssetClassType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HoldingRecordRepository extends JpaRepository<HoldingRecord, Long> {
    List<HoldingRecord> findByInvestorProfileId(Long investorProfileId);
    List<HoldingRecord> findByUserId(Long userId);
    List<HoldingRecord> findByAssetClassAndInvestorProfileId(AssetClassType assetClass, Long investorProfileId);
    List<HoldingRecord> findByRecordDateAndInvestorProfileId(LocalDate recordDate, Long investorProfileId);
}