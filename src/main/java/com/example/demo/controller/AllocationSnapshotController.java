package com.example.demo.controller;

import com.example.demo.entity.AllocationSnapshotRecord;
import com.example.demo.service.AllocationSnapshotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/snapshots")
public class AllocationSnapshotController {

    private final AllocationSnapshotService snapshotService;

    public AllocationSnapshotController(
            AllocationSnapshotService snapshotService
    ) {
        this.snapshotService = snapshotService;
    }

    @PostMapping("/compute/{investorId}")
    public ResponseEntity<AllocationSnapshotRecord> compute(
            @PathVariable Long investorId
    ) {
        return ResponseEntity.ok(snapshotService.computeSnapshot(investorId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AllocationSnapshotRecord> getById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(snapshotService.getSnapshotById(id));
    }

    @GetMapping
    public ResponseEntity<List<AllocationSnapshotRecord>> getAll() {
        return ResponseEntity.ok(snapshotService.getAllSnapshots());
    }
}
