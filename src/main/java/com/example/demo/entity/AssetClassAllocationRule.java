package com.example.demo.entity;

import com.example.demo.entity.enums.AssetClassType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "allocation_rules")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AssetClassAllocationRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long investorId;

    @Enumerated(EnumType.STRING)
    private AssetClassType assetClass;

    private Double targetPercentage;
    private Boolean active;

    public AssetClassAllocationRule(Long investorId, AssetClassType assetClass, Double targetPercentage, Boolean active) {
        this.investorId = investorId;
        this.assetClass = assetClass;
        this.targetPercentage = targetPercentage;
        this.active = active;
    }
}