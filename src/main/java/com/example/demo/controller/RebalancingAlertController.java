package com.example.demo.controller;

import com.example.demo.entity.RebalancingAlertRecord;
import com.example.demo.service.impl.RebalancingAlertServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class RebalancingAlertController {

    private final RebalancingAlertServiceImpl alertService;

    public RebalancingAlertController(RebalancingAlertServiceImpl alertService) {
        this.alertService = alertService;
    }

    @PostMapping
    public ResponseEntity<RebalancingAlertRecord> createAlert(@RequestBody RebalancingAlertRecord alert) {
        // Validates currentPercentage > targetPercentage (Priority 33)
        return ResponseEntity.ok(alertService.createAlert(alert));
    }

    @GetMapping("/investor/{investorId}")
    public ResponseEntity<List<RebalancingAlertRecord>> getAlertsByInvestor(@PathVariable Long investorId) {
        return ResponseEntity.ok(alertService.getAlertsByInvestor(investorId));
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<RebalancingAlertRecord> resolveAlert(@PathVariable Long id) {
        // Marks alert as resolved (Priority 34)
        return ResponseEntity.ok(alertService.resolveAlert(id));
    }
}