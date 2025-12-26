package com.example.demo.repository;
import com.example.demo.entity.HoldingRecord;
import com.example.demo.entity.enums.AssetClassType;
import org.springframework.data.jpa.repository.*;
import java.util.List;

public interface HoldingRecordRepository extends JpaRepository<HoldingRecord, Long> {
    List<HoldingRecord> findByInvestorId(Long investorId);
    
    @Query("SELECT h FROM HoldingRecord h WHERE h.currentValue > ?1")
    List<HoldingRecord> findByValueGreaterThan(Double value);
    
    @Query("SELECT h FROM HoldingRecord h WHERE h.investorId = ?1 AND h.assetClass = ?2")
    List<HoldingRecord> findByInvestorAndAssetClass(Long investorId, AssetClassType assetClass);
}