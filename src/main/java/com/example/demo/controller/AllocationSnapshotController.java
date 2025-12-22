package com.example.demo.controller;

import com.example.demo.entity.AllocationSnapshot;
import com.example.demo.service.AllocationSnapshotService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/snapshots")
public class AllocationSnapshotController {

    private final AllocationSnapshotService allocationSnapshotService;

    public AllocationSnapshotController(AllocationSnapshotService allocationSnapshotService) {
        this.allocationSnapshotService = allocationSnapshotService;
    }

    @PostMapping("/investor/{investorId}")
    public ResponseEntity<AllocationSnapshot> createSnapshot(@PathVariable Long investorId) {
        AllocationSnapshot snapshot = allocationSnapshotService.createSnapshot(investorId);
        if (snapshot != null) {
            return ResponseEntity.ok(snapshot);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AllocationSnapshot> getSnapshotById(@PathVariable Long id) {
        AllocationSnapshot snapshot = allocationSnapshotService.getSnapshotById(id);
        if (snapshot != null) {
            return ResponseEntity.ok(snapshot);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/investor/{investorId}")
    public ResponseEntity<List<AllocationSnapshot>> getSnapshotsByInvestor(
            @PathVariable Long investorId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<AllocationSnapshot> snapshots = allocationSnapshotService.getSnapshotsByInvestor(investorId, startDate, endDate);
        return ResponseEntity.ok(snapshots);
    }

    @GetMapping
    public ResponseEntity<List<AllocationSnapshot>> getAllSnapshots() {
        List<AllocationSnapshot> snapshots = allocationSnapshotService.getAllSnapshots();
        return ResponseEntity.ok(snapshots);
    }
}