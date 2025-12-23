package com.example.demo.Impl;

import com.example.demo.entity.AllocationSnapshot;
import com.example.demo.entity.InvestorProfile;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.AssetClassType;
import com.example.demo.repository.AllocationSnapshotRepository;
import com.example.demo.repository.InvestorProfileRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AllocationSnapshotServiceImpl implements AllocationSnapshotService {
    
    @Autowired
    private AllocationSnapshotRepository snapshotRepository;
    
    @Autowired
    private InvestorProfileRepository investorProfileRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private HoldingRecordServiceImpl holdingRecordService;
    
    @Override
    public AllocationSnapshot createSnapshot(Long investorProfileId) {
        InvestorProfile profile = investorProfileRepository.findById(investorProfileId).orElse(null);
        User user = profile != null ? profile.getUser() : null;
        
        if (profile == null || user == null) {
            return null;
        }
        
        Double totalPortfolioValue = holdingRecordService.calculateTotalPortfolioValue(investorProfileId);
        Map<String, Double> allocations = calculateAssetAllocations(investorProfileId);
        
        AllocationSnapshot snapshot = new AllocationSnapshot();
        snapshot.setSnapshotTimestamp(LocalDateTime.now());
        snapshot.setTotalPortfolioValue(totalPortfolioValue);
        snapshot.setAssetAllocations(allocations);
        snapshot.setUser(user);
        snapshot.setInvestorProfile(profile);
        
        return snapshotRepository.save(snapshot);
    }
    
    @Override
    public AllocationSnapshot getSnapshotById(Long id) {
        return snapshotRepository.findById(id).orElse(null);
    }
    
    @Override
    public List<AllocationSnapshot> getSnapshotsByInvestorProfile(Long investorProfileId) {
        return snapshotRepository.findByInvestorProfileId(investorProfileId);
    }
    
    @Override
    public List<AllocationSnapshot> getSnapshotsByTimeRange(LocalDateTime start, LocalDateTime end) {
        return snapshotRepository.findBySnapshotTimestampBetween(start, end);
    }
    
    @Override
    public Map<String, Double> calculateAssetAllocations(Long investorProfileId) {
        Map<String, Double> allocations = new HashMap<>();
        Double totalPortfolioValue = holdingRecordService.calculateTotalPortfolioValue(investorProfileId);
        
        if (totalPortfolioValue > 0) {
            for (AssetClassType assetClass : AssetClassType.values()) {
                Double assetValue = holdingRecordService.calculateAssetClassValue(investorProfileId, assetClass);
                Double percentage = (assetValue / totalPortfolioValue) * 100;
                allocations.put(assetClass.name(), percentage);
            }
        }
        
        return allocations;
    }
    
    @Override
    public void deleteSnapshot(Long id) {
        snapshotRepository.deleteById(id);
    }
}