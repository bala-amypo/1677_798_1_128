package com.example.demo.entity;

import com.example.demo.entity.enums.AssetClassType;
import jakarta.persistence.*;

@Entity
public class AssetClassAllocationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long investorId;

    @Enumerated(EnumType.STRING)
    private AssetClassType assetClass;

    private Double targetPercentage;
    private Boolean active = true;

    public void validate() {
        if (targetPercentage < 0 || targetPercentage > 100) {
            throw new IllegalArgumentException("between 0 and 100");
        }
    }

    // getters and setters
}
