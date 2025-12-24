package com.example.demo.controller;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.service.HoldingRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/holdings")
@Tag(name = "Holding Records", description = "Portfolio holding management endpoints")
public class HoldingRecordController {
    
    private final HoldingRecordService holdingRecordService;
    
    public HoldingRecordController(HoldingRecordService holdingRecordService) {
        this.holdingRecordService = holdingRecordService;
    }
    
    @PostMapping("/")
    @Operation(summary = "Record a new holding")
    public ResponseEntity<HoldingRecord> recordHolding(@RequestBody HoldingRecord holding) {
        HoldingRecord recorded = holdingRecordService.recordHolding(holding);
        return ResponseEntity.ok(recorded);
    }
    
    @GetMapping("/investor/{investorId}")
    @Operation(summary = "Get holdings by investor")
    public ResponseEntity<List<HoldingRecord>> getHoldingsByInvestor(@PathVariable Long investorId) {
        List<HoldingRecord> holdings = holdingRecordService.getHoldingsByInvestor(investorId);
        return ResponseEntity.ok(holdings);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get holding by ID")
    public ResponseEntity<HoldingRecord> getHoldingById(@PathVariable Long id) {
        HoldingRecord holding = holdingRecordService.getHoldingById(id);
        return ResponseEntity.ok(holding);
    }
    
    @GetMapping("/")
    @Operation(summary = "Get all holdings")
    public ResponseEntity<List<HoldingRecord>> getAllHoldings() {
        List<HoldingRecord> holdings = holdingRecordService.getAllHoldings();
        return ResponseEntity.ok(holdings);
    }
}