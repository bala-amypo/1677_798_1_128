// package com.example.demo.controller;

// import com.example.demo.entity.RebalancingAlertRecord;
// import com.example.demo.service.RebalancingAlertService;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// @RestController
// @RequestMapping("/api/alerts")
// public class RebalancingAlertController {

//     private final RebalancingAlertService service;

//     public RebalancingAlertController(RebalancingAlertService service) {
//         this.service = service;
//     }

//     @PostMapping
//     public RebalancingAlertRecord create(@RequestBody RebalancingAlertRecord a) {
//         return service.createAlert(a);
//     }

//     @PutMapping("/{id}/resolve")
//     public void resolve(@PathVariable Long id) {
//         service.resolveAlert(id);
//     }

//     @GetMapping("/investor/{id}")
//     public List<RebalancingAlertRecord> byInvestor(@PathVariable Long id) {
//         return service.getAlertsByInvestor(id);
//     }

//     @GetMapping("/{id}")
//     public RebalancingAlertRecord get(@PathVariable Long id) {
//         return service.getAlertById(id);
//     }

//     @GetMapping
//     public List<RebalancingAlertRecord> all() {
//         return service.getAllAlerts();
//     }
// }
