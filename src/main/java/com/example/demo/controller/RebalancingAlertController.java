// src/main/java/com/example/demo/controller/RebalancingAlertController.java
package com.example.demo.controller;

import com.example.demo.entity.RebalancingAlertRecord;
import com.example.demo.service.RebalancingAlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class RebalancingAlertController {

    private final RebalancingAlertService service;

    public RebalancingAlertController(RebalancingAlertService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RebalancingAlertRecord> create(
            @RequestBody RebalancingAlertRecord alert) {
        return ResponseEntity.ok(service.createAlert(alert));
    }

    @PostMapping("/{id}/resolve")
    public ResponseEntity<RebalancingAlertRecord> resolve(@PathVariable Long id) {
        return ResponseEntity.ok(service.resolveAlert(id));
    }

    @GetMapping("/investor/{investorId}")
    public ResponseEntity<List<RebalancingAlertRecord>> byInvestor(
            @PathVariable Long investorId) {
        return ResponseEntity.ok(service.getAlertsByInvestor(investorId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RebalancingAlertRecord> byId(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAlertById(id));
    }

    @GetMapping
    public ResponseEntity<List<RebalancingAlertRecord>> all() {
        return ResponseEntity.ok(service.getAllAlerts());
    }
}
