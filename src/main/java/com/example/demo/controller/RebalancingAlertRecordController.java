package com.example.demo.controller;

import com.example.demo.entity.RebalancingAlertRecord;
import com.example.demo.service.RebalancingAlertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/alerts")
@Tag(name = "Rebalancing Alerts", description = "Portfolio rebalancing alert endpoints")
public class RebalancingAlertRecordController {
    
    private final RebalancingAlertService rebalancingAlertService;
    
    public RebalancingAlertRecordController(RebalancingAlertService rebalancingAlertService) {
        this.rebalancingAlertService = rebalancingAlertService;
    }
    
    @PostMapping("/")
    @Operation(summary = "Create a new alert")
    public ResponseEntity<RebalancingAlertRecord> createAlert(@RequestBody RebalancingAlertRecord alert) {
        RebalancingAlertRecord created = rebalancingAlertService.createAlert(alert);
        return ResponseEntity.ok(created);
    }
    
    @PutMapping("/{id}/resolve")
    @Operation(summary = "Resolve an alert")
    public ResponseEntity<RebalancingAlertRecord> resolveAlert(@PathVariable Long id) {
        RebalancingAlertRecord resolved = rebalancingAlertService.resolveAlert(id);
        return ResponseEntity.ok(resolved);
    }
    
    @GetMapping("/investor/{investorId}")
    @Operation(summary = "Get alerts by investor")
    public ResponseEntity<List<RebalancingAlertRecord>> getAlertsByInvestor(@PathVariable Long investorId) {
        List<RebalancingAlertRecord> alerts = rebalancingAlertService.getAlertsByInvestor(investorId);
        return ResponseEntity.ok(alerts);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get alert by ID")
    public ResponseEntity<RebalancingAlertRecord> getAlertById(@PathVariable Long id) {
        RebalancingAlertRecord alert = rebalancingAlertService.getAlertById(id);
        return ResponseEntity.ok(alert);
    }
    
    @GetMapping("/")
    @Operation(summary = "Get all alerts")
    public ResponseEntity<List<RebalancingAlertRecord>> getAllAlerts() {
        List<RebalancingAlertRecord> alerts = rebalancingAlertService.getAllAlerts();
        return ResponseEntity.ok(alerts);
    }
}