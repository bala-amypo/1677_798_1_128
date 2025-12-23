package com.example.demo.controller;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.service.HoldingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/holding-records")
public class HoldingRecordController {
    
    @Autowired
    private HoldingRecordService holdingRecordService;
    
    @PostMapping
    public ResponseEntity<HoldingRecord> createHolding(@RequestBody HoldingRecord holding) {
        HoldingRecord createdHolding = holdingRecordService.createHolding(holding);
        return new ResponseEntity<>(createdHolding, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<HoldingRecord> updateHolding(@PathVariable Long id, @RequestBody HoldingRecord holding) {
        HoldingRecord updatedHolding = holdingRecordService.updateHolding(id, holding);
        return ResponseEntity.ok(updatedHolding);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHolding(@PathVariable Long id) {
        holdingRecordService.deleteHolding(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<HoldingRecord> getHoldingById(@PathVariable Long id) {
        HoldingRecord holding = holdingRecordService.getHoldingById(id);
        return ResponseEntity.ok(holding);
    }
    
    @GetMapping("/portfolio/{portfolioId}")
    public ResponseEntity<List<HoldingRecord>> getHoldingsByPortfolioId(@PathVariable Long portfolioId) {
        List<HoldingRecord> holdings = holdingRecordService.getHoldingsByPortfolioId(portfolioId);
        return ResponseEntity.ok(holdings);
    }
    
    @GetMapping("/portfolio/{portfolioId}/date")
    public ResponseEntity<List<HoldingRecord>> getHoldingsByPortfolioIdAndDate(
            @PathVariable Long portfolioId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<HoldingRecord> holdings = holdingRecordService.getHoldingsByPortfolioIdAndDate(portfolioId, date);
        return ResponseEntity.ok(holdings);
    }
    
    @GetMapping("/asset-class/{assetClass}")
    public ResponseEntity<List<HoldingRecord>> getHoldingsByAssetClass(@PathVariable String assetClass) {
        List<HoldingRecord> holdings = holdingRecordService.getHoldingsByAssetClass(assetClass);
        return ResponseEntity.ok(holdings);
    }
    
    @GetMapping("/portfolio/{portfolioId}/value")
    public ResponseEntity<BigDecimal> calculatePortfolioValue(@PathVariable Long portfolioId) {
        BigDecimal value = holdingRecordService.calculatePortfolioValue(portfolioId);
        return ResponseEntity.ok(value);
    }
    
    @GetMapping("/portfolio/{portfolioId}/allocation")
    public ResponseEntity<Map<String, BigDecimal>> calculateAssetClassAllocation(@PathVariable Long portfolioId) {
        Map<String, BigDecimal> allocation = holdingRecordService.calculateAssetClassAllocation(portfolioId);
        return ResponseEntity.ok(allocation);
    }
}