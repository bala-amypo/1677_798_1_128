package com.example.demo.repository;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.entity.enums.AssetClassType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HoldingRecordRepository extends JpaRepository<HoldingRecord, Long> {
    List<HoldingRecord> findByInvestorId(Long investorId);
    List<HoldingRecord> findByCurrentValueGreaterThan(Double value);
    
    @Query("SELECT h FROM HoldingRecord h WHERE h.investorId = :investorId AND h.assetClass = :assetClass")
    List<HoldingRecord> findByInvestorAndAssetClass(@Param("investorId") Long investorId, 
                                                   @Param("assetClass") AssetClassType assetClass);
}