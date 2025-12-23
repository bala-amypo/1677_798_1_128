package com.example.demo.controller;

import com.example.demo.Impl.HoldingRecordService;
import com.example.demo.entity.HoldingRecord;
import com.example.demo.entity.enums.AssetClassType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/holding-records")
public class HoldingRecordController {
    
    @Autowired
    private HoldingRecordService holdingRecordService;
    
    @PostMapping
    public ResponseEntity<HoldingRecord> createHoldingRecord(
            @RequestParam Long userId,
            @RequestParam Long investorProfileId,
            @RequestParam AssetClassType assetClass,
            @RequestParam String assetName,
            @RequestParam Integer quantity,
            @RequestParam Double pricePerUnit,
            @RequestParam LocalDate recordDate) {
        HoldingRecord record = holdingRecordService.createHoldingRecord(
                userId, investorProfileId, assetClass, assetName, quantity, pricePerUnit, recordDate);
        if (record != null) {
            return ResponseEntity.ok(record);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
    @GetMapping("/investor-profile/{investorProfileId}")
    public ResponseEntity<List<HoldingRecord>> getHoldingRecordsByInvestorProfile(
            @PathVariable Long investorProfileId) {
        List<HoldingRecord> records = holdingRecordService.getHoldingRecordsByInvestorProfile(investorProfileId);
        return ResponseEntity.ok(records);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<HoldingRecord>> getHoldingRecordsByUser(@PathVariable Long userId) {
        List<HoldingRecord> records = holdingRecordService.getHoldingRecordsByUser(userId);
        return ResponseEntity.ok(records);
    }
    
    @GetMapping("/portfolio-value/{investorProfileId}")
    public ResponseEntity<Double> getTotalPortfolioValue(@PathVariable Long investorProfileId) {
        Double value = holdingRecordService.calculateTotalPortfolioValue(investorProfileId);
        return ResponseEntity.ok(value);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<HoldingRecord> updateHoldingRecord(
            @PathVariable Long id,
            @RequestParam Integer quantity,
            @RequestParam Double pricePerUnit) {
        HoldingRecord record = holdingRecordService.updateHoldingRecord(id, quantity, pricePerUnit);
        if (record != null) {
            return ResponseEntity.ok(record);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHoldingRecord(@PathVariable Long id) {
        holdingRecordService.deleteHoldingRecord(id);
        return ResponseEntity.ok().build();
    }
}