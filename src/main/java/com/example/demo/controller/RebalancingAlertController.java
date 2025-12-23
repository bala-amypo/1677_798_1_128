package com.example.demo.controller;

import com.example.demo.entity.RebalancingAlertRecord;
import com.example.demo.entity.enums.AlertSeverity;
import com.example.demo.service.RebalancingAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rebalancing-alerts")
public class RebalancingAlertController {
    
    @Autowired
    private RebalancingAlertService rebalancingAlertService;
    
    @PostMapping
    public ResponseEntity<RebalancingAlertRecord> createAlert(@RequestBody RebalancingAlertRecord alert) {
        RebalancingAlertRecord createdAlert = rebalancingAlertService.createAlert(alert);
        return new ResponseEntity<>(createdAlert, HttpStatus.CREATED);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RebalancingAlertRecord>> getAlertsByUserId(@PathVariable Long userId) {
        List<RebalancingAlertRecord> alerts = rebalancingAlertService.getAlertsByUserId(userId);
        return ResponseEntity.ok(alerts);
    }
    
    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<List<RebalancingAlertRecord>> getUnreadAlerts(@PathVariable Long userId) {
        List<RebalancingAlertRecord> alerts = rebalancingAlertService.getUnreadAlertsByUserId(userId);
        return ResponseEntity.ok(alerts);
    }
    
    @GetMapping("/user/{userId}/unresolved")
    public ResponseEntity<List<RebalancingAlertRecord>> getUnresolvedAlerts(@PathVariable Long userId) {
        List<RebalancingAlertRecord> alerts = rebalancingAlertService.getUnresolvedAlertsByUserId(userId);
        return ResponseEntity.ok(alerts);
    }
    
    @GetMapping("/portfolio/{portfolioId}")
    public ResponseEntity<List<RebalancingAlertRecord>> getAlertsByPortfolioId(@PathVariable Long portfolioId) {
        List<RebalancingAlertRecord> alerts = rebalancingAlertService.getAlertsByPortfolioId(portfolioId);
        return ResponseEntity.ok(alerts);
    }
    
    @GetMapping("/severity/{severity}")
    public ResponseEntity<List<RebalancingAlertRecord>> getAlertsBySeverity(@PathVariable AlertSeverity severity) {
        List<RebalancingAlertRecord> alerts = rebalancingAlertService.getAlertsBySeverity(severity);
        return ResponseEntity.ok(alerts);
    }
    
    @PatchMapping("/{alertId}/read")
    public ResponseEntity<Void> markAlertAsRead(@PathVariable Long alertId) {
        rebalancingAlertService.markAlertAsRead(alertId);
        return ResponseEntity.ok().build();
    }
    
    @PatchMapping("/{alertId}/resolved")
    public ResponseEntity<Void> markAlertAsResolved(@PathVariable Long alertId) {
        rebalancingAlertService.markAlertAsResolved(alertId);
        return ResponseEntity.ok().build();
    }
    
    @PatchMapping("/user/{userId}/read-all")
    public ResponseEntity<Void> markAllAlertsAsRead(@PathVariable Long userId) {
        rebalancingAlertService.markAllAlertsAsRead(userId);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{alertId}")
    public ResponseEntity<Void> deleteAlert(@PathVariable Long alertId) {
        rebalancingAlertService.deleteAlert(alertId);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/portfolio/{portfolioId}")
    public ResponseEntity<Void> deleteAlertsByPortfolioId(@PathVariable Long portfolioId) {
        rebalancingAlertService.deleteAlertsByPortfolioId(portfolioId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/portfolio/{portfolioId}/check")
    public ResponseEntity<List<RebalancingAlertRecord>> checkForRebalancingAlerts(@PathVariable Long portfolioId) {
        List<RebalancingAlertRecord> alerts = rebalancingAlertService.checkForRebalancingAlerts(portfolioId);
        return ResponseEntity.ok(alerts);
    }
}