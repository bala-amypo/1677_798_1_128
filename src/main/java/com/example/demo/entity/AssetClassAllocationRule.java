package com.example.demo.entity;

import com.example.demo.entity.enums.AssetClassType;
import jakarta.persistence.*;

@Entity
@Table(
    name = "asset_allocation_rules",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"investorId", "assetClass"})
    }
)
public class AssetClassAllocationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long investorId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssetClassType assetClass;

    @Column(nullable = false)
    private Double targetPercentage;

    @Column(nullable = false)
    private Boolean active = true;

    public AssetClassAllocationRule() {}

    public AssetClassAllocationRule(
            Long investorId,
            AssetClassType assetClass,
            Double targetPercentage,
            Boolean active
    ) {
        this.investorId = investorId;
        this.assetClass = assetClass;
        this.targetPercentage = targetPercentage;
        this.active = active;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getInvestor
