package com.example.demo.Impl;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.entity.InvestorProfile;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.AssetClassType;
import com.example.demo.repository.HoldingRecordRepository;
import com.example.demo.repository.InvestorProfileRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HoldingRecordServiceImpl implements HoldingRecordService {
    
    @Autowired
    private HoldingRecordRepository holdingRecordRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private InvestorProfileRepository investorProfileRepository;
    
    @Override
    public HoldingRecord createHoldingRecord(Long userId, Long investorProfileId, AssetClassType assetClass, 
                                            String assetName, Integer quantity, Double pricePerUnit, LocalDate recordDate) {
        User user = userRepository.findById(userId).orElse(null);
        InvestorProfile profile = investorProfileRepository.findById(investorProfileId).orElse(null);
        
        if (user == null || profile == null) {
            return null;
        }
        
        HoldingRecord record = new HoldingRecord();
        record.setAssetClass(assetClass);
        record.setAssetName(assetName);
        record.setQuantity(quantity);
        record.setPricePerUnit(pricePerUnit);
        record.setTotalValue(quantity * pricePerUnit);
        record.setRecordDate(recordDate);
        record.setUser(user);
        record.setInvestorProfile(profile);
        
        return holdingRecordRepository.save(record);
    }
    
    @Override
    public List<HoldingRecord> getHoldingRecordsByInvestorProfile(Long investorProfileId) {
        return holdingRecordRepository.findByInvestorProfileId(investorProfileId);
    }
    
    @Override
    public List<HoldingRecord> getHoldingRecordsByUser(Long userId) {
        return holdingRecordRepository.findByUserId(userId);
    }
    
    @Override
    public List<HoldingRecord> getHoldingRecordsByAssetClass(Long investorProfileId, AssetClassType assetClass) {
        return holdingRecordRepository.findByAssetClassAndInvestorProfileId(assetClass, investorProfileId);
    }
    
    @Override
    public HoldingRecord updateHoldingRecord(Long id, Integer quantity, Double pricePerUnit) {
        HoldingRecord record = holdingRecordRepository.findById(id).orElse(null);
        if (record != null) {
            record.setQuantity(quantity);
            record.setPricePerUnit(pricePerUnit);
            record.setTotalValue(quantity * pricePerUnit);
            return holdingRecordRepository.save(record);
        }
        return null;
    }
    
    @Override
    public void deleteHoldingRecord(Long id) {
        holdingRecordRepository.deleteById(id);
    }
    
    @Override
    public Double calculateTotalPortfolioValue(Long investorProfileId) {
        List<HoldingRecord> holdings = holdingRecordRepository.findByInvestorProfileId(investorProfileId);
        return holdings.stream()
                .mapToDouble(HoldingRecord::getTotalValue)
                .sum();
    }
    
    @Override
    public Double calculateAssetClassValue(Long investorProfileId, AssetClassType assetClass) {
        List<HoldingRecord> holdings = holdingRecordRepository.findByAssetClassAndInvestorProfileId(assetClass, investorProfileId);
        return holdings.stream()
                .mapToDouble(HoldingRecord::getTotalValue)
                .sum();
    }
}