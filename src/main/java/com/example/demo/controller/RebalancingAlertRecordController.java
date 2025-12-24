package com.example.demo.controller;

import com.example.demo.entity.RebalancingAlertRecord;
import com.example.demo.service.RebalancingAlertService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@Tag(name = "Rebalancing Alerts")
public class RebalancingAlertRecordController {

    private final RebalancingAlertRecordService service;

    public RebalancingAlertRecordController(RebalancingAlertRecordService service) {
        this.service = service;
    }

    @PostMapping
    public RebalancingAlertRecord create(@RequestBody RebalancingAlertRecord alert) {
        return service.createAlert(alert);
    }

    @PutMapping("/{id}/resolve")
    public RebalancingAlertRecord resolve(@PathVariable Long id) {
        return service.resolveAlert(id);
    }

    @GetMapping("/investor/{investorId}")
    public List<RebalancingAlertRecord> byInvestor(@PathVariable Long investorId) {
        return service.getAlertsByInvestor(investorId);
    }

    @GetMapping("/{id}")
    public RebalancingAlertRecord get(@PathVariable Long id) {
        return service.getAlertById(id);
    }

    @GetMapping
    public List<RebalancingAlertRecord> all() {
        return service.getAllAlerts();
    }
}
