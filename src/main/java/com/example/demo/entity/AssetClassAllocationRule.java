package com.example.demo.entity;

import com.example.demo.entity.enums.AssetClassType;
import jakarta.persistence.*;

@Entity
@Table(name = "asset_allocation_rules")
public class AssetClassAllocationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AssetClassType assetClass;

    private double targetPercentage;

    private boolean active;

    // ===== Validation =====
    public void validate() {
        if (assetClass == null) {
            throw new IllegalArgumentException("Asset class is required");
        }
        if (targetPercentage <= 0 || targetPercentage > 100) {
            throw new IllegalArgumentException("Target percentage must be between 0 and 100");
        }
    }

    // ===== Getters & Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public AssetClassType getAssetClass() { return assetClass; }
    public void setAssetClass(AssetClassType assetClass) { this.assetClass = assetClass; }

    public double getTargetPercentage() { return targetPercentage; }
    public void setTargetPercentage(double targetPercentage) { this.targetPercentage = targetPercentage; }

    public boolean getActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
