package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class AllocationSnapshotRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long investorId;
    private LocalDateTime snapshotDate = LocalDateTime.now();
    private Double totalPortfolioValue;
    @Column(columnDefinition = "TEXT") private String allocationJson;
}