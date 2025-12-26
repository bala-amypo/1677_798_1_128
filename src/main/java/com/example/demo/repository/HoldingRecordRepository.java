// src/main/java/com/example/demo/repository/HoldingRecordRepository.java
package com.example.demo.repository;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.entity.enums.AssetClassType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HoldingRecordRepository extends JpaRepository<HoldingRecord, Long> {

    List<HoldingRecord> findByInvestorId(Long investorId);

    // custom JPQL matching tests that call findByValueGreaterThan(...)
    @Query("select h from HoldingRecord h where h.currentValue > :value")
    List<HoldingRecord> findByValueGreaterThan(@Param("value") Double value);

    List<HoldingRecord> findByInvestorIdAndAssetClass(Long investorId,
                                                      AssetClassType assetClass);

    @Query("select h from HoldingRecord h " +
           "where h.investorId = :investorId and h.assetClass = :assetClass")
    List<HoldingRecord> findByInvestorAndAssetClass(@Param("investorId") Long investorId,
                                                    @Param("assetClass") AssetClassType assetClass);
}
