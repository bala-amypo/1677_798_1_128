package com.example.demo.entity;

import com.example.demo.entity.enums.AssetClassType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "holding_records")
public class HoldingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long investorId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssetClassType assetClass;

    @Positive
    @Column(nullable = false)
    private double value;

    public HoldingRecord() {}

    public HoldingRecord(Long investorId, AssetClassType assetClass, double value) {
        this.investorId = investorId;
        this.assetClass = assetClass;
        this.value = value;
    }

    public Long getId() {
        return id;
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
