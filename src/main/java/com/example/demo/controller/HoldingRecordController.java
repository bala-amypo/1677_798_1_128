// src/main/java/com/example/demo/controller/HoldingRecordController.java
package com.example.demo.controller;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.service.HoldingRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/holdings")
public class HoldingRecordController {

    private final HoldingRecordService service;

    public HoldingRecordController(HoldingRecordService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<HoldingRecord> record(@RequestBody HoldingRecord record) {
        return ResponseEntity.ok(service.recordHolding(record));
    }

    @GetMapping("/investor/{investorId}")
    public ResponseEntity<List<HoldingRecord>> byInvestor(@PathVariable Long investorId) {
        return ResponseEntity.ok(service.getHoldingsByInvestor(investorId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HoldingRecord> byId(@PathVariable Long id) {
        return service.getHoldingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<HoldingRecord>> all() {
        return ResponseEntity.ok(service.getAllHoldings());
    }
}
