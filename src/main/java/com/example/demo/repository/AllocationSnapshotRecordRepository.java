// src/main/java/com/example/demo/repository/AllocationSnapshotRecordRepository.java
package com.example.demo.repository;

import com.example.demo.entity.AllocationSnapshotRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllocationSnapshotRecordRepository
        extends JpaRepository<AllocationSnapshotRecord, Long> {
}
