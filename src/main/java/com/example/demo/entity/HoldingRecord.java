package com.example.demo.entity;

import com.example.demo.entity.enums.AssetClassType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class HoldingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long investorId;

    @Enumerated(EnumType.STRING)
    private AssetClassType assetClass;

    private Double currentValue;
    private LocalDateTime snapshotDate = LocalDateTime.now();

    public void validate() {
        if (currentValue <= 0) {
            throw new IllegalArgumentException("must be > 0");
        }
    }

    // getters & setters
}
