package com.example.demo.controller;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.service.impl.HoldingRecordServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/holdings")
public class HoldingRecordController {

    private final HoldingRecordServiceImpl holdingService;

    public HoldingRecordController(HoldingRecordServiceImpl holdingService) {
        this.holdingService = holdingService;
    }

    @PostMapping
    public ResponseEntity<HoldingRecord> recordHolding(@RequestBody HoldingRecord holding) {
        // Triggers validation logic for current value > 0 (Priority 15)
        return ResponseEntity.ok(holdingService.recordHolding(holding));
    }

    @GetMapping("/investor/{investorId}")
    public ResponseEntity<List<HoldingRecord>> getHoldingsByInvestor(@PathVariable Long investorId) {
        return ResponseEntity.ok(holdingService.getHoldingsByInvestor(investorId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HoldingRecord> getHoldingById(@PathVariable Long id) {
        return holdingService.getHoldingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}