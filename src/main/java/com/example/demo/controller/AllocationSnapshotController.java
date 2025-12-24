package com.example.demo.controller;

import com.example.demo.entity.AllocationSnapshotRecord;
import com.example.demo.service.AllocationSnapshotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/snapshots")
@Tag(name = "Allocation Snapshots", description = "Portfolio allocation snapshot endpoints")
public class AllocationSnapshotController {
    
    private final AllocationSnapshotService allocationSnapshotService;
    
    public AllocationSnapshotController(AllocationSnapshotService allocationSnapshotService) {
        this.allocationSnapshotService = allocationSnapshotService;
    }
    
    @PostMapping("/compute/{investorId}")
    @Operation(summary = "Compute allocation snapshot for investor")
    public ResponseEntity<AllocationSnapshotRecord> computeSnapshot(@PathVariable Long investorId) {
        AllocationSnapshotRecord snapshot = allocationSnapshotService.computeSnapshot(investorId);
        return ResponseEntity.ok(snapshot);
    }
    
    @GetMapping("/investor/{investorId}")
    @Operation(summary = "Get snapshots by investor")
    public ResponseEntity<List<AllocationSnapshotRecord>> getSnapshotsByInvestor(@PathVariable Long investorId) {
        List<AllocationSnapshotRecord> snapshots = allocationSnapshotService.getSnapshotsByInvestor(investorId);
        return ResponseEntity.ok(snapshots);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get snapshot by ID")
    public ResponseEntity<AllocationSnapshotRecord> getSnapshotById(@PathVariable Long id) {
        AllocationSnapshotRecord snapshot = allocationSnapshotService.getSnapshotById(id);
        return ResponseEntity.ok(snapshot);
    }
    
    @GetMapping("/")
    @Operation(summary = "Get all snapshots")
    public ResponseEntity<List<AllocationSnapshotRecord>> getAllSnapshots() {
        List<AllocationSnapshotRecord> snapshots = allocationSnapshotService.getAllSnapshots();
        return ResponseEntity.ok(snapshots);
    }
}