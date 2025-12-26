package com.example.demo.service;

import com.example.demo.entity.AllocationSnapshotRecord;

public interface AllocationSnapshotService {

    AllocationSnapshotRecord createSnapshot(Long investorId);
}
