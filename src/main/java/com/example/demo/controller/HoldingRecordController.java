package com.example.demo.entity;

import com.example.demo.entity.enums.AssetClassType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "holding_records")
public class HoldingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long investorId;

    @Enumerated(EnumType.STRING)
    private AssetClassType assetClass;

    private Double currentValue;

    private LocalDateTime snapshotDate;

    // ===== GETTERS / SETTERS =====

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

    public LocalDateTime getSnapshotDate() {
        return snapshotDate;
    }

    public void setSnapshotDate(LocalDateTime snapshotDate) {
        this.snapshotDate = snapshotDate;
    }

    // ===== VALIDATION =====
    public void validate() {
        if (currentValue == null || currentValue <= 0) {
            throw new IllegalArgumentException("must be > 0");
        }
    }
}
