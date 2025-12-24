// package com.example.demo.controller;

// import com.example.demo.service.AllocationSnapshotService;
// import org.springframework.web.bind.annotation.*;

// import java.util.Map;

// @RestController
// @RequestMapping("/allocation")
// public class AllocationSnapshotController {

//     private final AllocationSnapshotService service;

//     public AllocationSnapshotController(AllocationSnapshotService service) {
//         this.service = service;
//     }

//     @GetMapping("/{investorId}")
//     public Map<String, Double> getAllocation(@PathVariable Long investorId) {
//         return service.calculateAllocation(investorId);
//     }
// }
