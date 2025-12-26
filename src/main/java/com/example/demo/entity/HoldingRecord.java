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

    @Enumerated(EnumType.STRING)
    private AssetClassType assetClass;

    private double currentValue;

    private LocalDateTime timestamp;

    // Default constructor
    public HoldingRecord() {}

    // Existing constructor
    public HoldingRecord(Long id, AssetClassType assetClass, double currentValue) {
        this.id = id;
        this.assetClass = assetClass;
        this.currentValue = currentValue;
        this.timestamp = LocalDateTime.now();
    }

    // New constructor to satisfy tests
    public HoldingRecord(Long id, AssetClassType assetClass, double currentValue, LocalDateTime timestamp) {
        this.id = id;
        this.assetClass = assetClass;
        this.currentValue = currentValue;
        this.timestamp = timestamp;
    }

    // Getters & setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AssetClassType getAssetClass() {
        return assetClass;
    }

    public void setAssetClass(AssetClassType assetClass) {
        this.assetClass = assetClass;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
