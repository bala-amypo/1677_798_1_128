// src/main/java/com/example/demo/entity/HoldingRecord.java
package com.example.demo.entity;

import com.example.demo.entity.enums.AssetClassType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class HoldingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long investorId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssetClassType assetClass;

    @Column(nullable = false)
    private Double currentValue;

    @Column(nullable = false)
    private LocalDateTime valuationTime;

    public HoldingRecord() {
    }

    public HoldingRecord(Long investorId, AssetClassType assetClass,
                         Double currentValue, LocalDateTime valuationTime) {
        this.investorId = investorId;
        this.assetClass = assetClass;
        this.currentValue = currentValue;
        this.valuationTime = valuationTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInvestorId() {
        return investorId;
    }

    public void setInvestorId(Long investorId) {
        this.investorId = investorId;
    }

    public AssetClassType getAssetClass() {
        return assetClass;
    }

    public void setAssetClass(AssetClassType assetClass) {
        this.assetClass = assetClass;
    }

    public Double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Double currentValue) {
        this.currentValue = currentValue;
    }

    public LocalDateTime getValuationTime() {
        return valuationTime;
    }

    public void setValuationTime(LocalDateTime valuationTime) {
        this.valuationTime = valuationTime;
    }
}
