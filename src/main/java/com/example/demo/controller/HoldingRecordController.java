package com.example.demo.controller;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.service.impl.HoldingRecordServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/holdings")
@RequiredArgsConstructor
public class HoldingRecordController {
    private final HoldingRecordServiceImpl holdingRecordService;

    @PostMapping
    public ResponseEntity<HoldingRecord> record(@RequestBody HoldingRecord holding) {
        return ResponseEntity.ok(holdingRecordService.recordHolding(holding));
    }

    @GetMapping("/investor/{investorId}")
    public ResponseEntity<List<HoldingRecord>> getByInvestor(@PathVariable Long investorId) {
        return ResponseEntity.ok(holdingRecordService.getHoldingsByInvestor(investorId));
    }
}