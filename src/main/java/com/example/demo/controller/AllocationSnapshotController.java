package com.example.demo.controller;

import com.example.demo.entity.AllocationSnapshotRecord;
import com.example.demo.service.AllocationSnapshotService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/snapshots")
@Tag(name = "Allocation Snapshots")
public class AllocationSnapshotController {
    private final AllocationSnapshotService snapshotService;

    public AllocationSnapshotController(AllocationSnapshotService snapshotService) {
        this.snapshotService = snapshotService;
    }

    @PostMapping("/compute/{investorId}")
    public AllocationSnapshotRecord compute(@PathVariable Long investorId) {
        return snapshotService.computeSnapshot(investorId);
    }

    @GetMapping
    public List<AllocationSnapshotRecord> getAll() {
        return snapshotService.getAllSnapshots();
    }
}