package com.example.demo.service.impl;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.repository.InvestorProfileRepository;
import com.example.demo.service.InvestorProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InvestorProfileServiceImpl implements InvestorProfileService {
    
    @Autowired
    private InvestorProfileRepository investorProfileRepository;
    
    @Override
    @Transactional
    public InvestorProfile createProfile(Long userId, String riskTolerance, 
                                        String investmentHorizon, BigDecimal initialInvestment) {
        InvestorProfile profile = new InvestorProfile();
        profile.setUserId(userId);
        profile.setRiskTolerance(riskTolerance);
        profile.setInvestmentHorizon(investmentHorizon);
        profile.setInitialInvestment(initialInvestment);
        profile.setCurrentInvestment(initialInvestment);
        profile.setIsActive(true);
        profile.setCreatedAt(LocalDateTime.now());
        profile.setUpdatedAt(LocalDateTime.now());
        
        return investorProfileRepository.save(profile);
    }
    
    @Override
    public Optional<InvestorProfile> getProfileByUserId(Long userId) {
        return investorProfileRepository.findByUserId(userId);
    }
    
    @Override
    @Transactional
    public InvestorProfile updateProfile(Long userId, String riskTolerance, 
                                        String investmentHorizon, BigDecimal currentInvestment) {
        Optional<InvestorProfile> existingProfile = investorProfileRepository.findByUserId(userId);
        if (existingProfile.isPresent()) {
            InvestorProfile profile = existingProfile.get();
            profile.setRiskTolerance(riskTolerance);
            profile.setInvestmentHorizon(investmentHorizon);
            profile.setCurrentInvestment(currentInvestment);
            profile.setUpdatedAt(LocalDateTime.now());
            
            return investorProfileRepository.save(profile);
        }
        throw new RuntimeException("Investor profile not found for user ID: " + userId);
    }
    
    @Override
    @Transactional
    public void updateInvestorStatus(Long userId, boolean isActive) {
        Optional<InvestorProfile> existingProfile = investorProfileRepository.findByUserId(userId);
        if (existingProfile.isPresent()) {
            InvestorProfile profile = existingProfile.get();
            profile.setIsActive(isActive);
            profile.setUpdatedAt(LocalDateTime.now());
            investorProfileRepository.save(profile);
        } else {
            throw new RuntimeException("Investor profile not found for user ID: " + userId);
        }
    }
    
    @Override
    @Transactional
    public void updateCurrentInvestment(Long userId, BigDecimal currentInvestment) {
        Optional<InvestorProfile> existingProfile = investorProfileRepository.findByUserId(userId);
        if (existingProfile.isPresent()) {
            InvestorProfile profile = existingProfile.get();
            profile.setCurrentInvestment(currentInvestment);
            profile.setUpdatedAt(LocalDateTime.now());
            investorProfileRepository.save(profile);
        } else {
            throw new RuntimeException("Investor profile not found for user ID: " + userId);
        }
    }
    
    @Override
    public List<InvestorProfile> getAllActiveProfiles() {
        // Since we don't have a direct query, we'll filter in service layer
        List<InvestorProfile> allProfiles = investorProfileRepository.findAll();
        return allProfiles.stream()
                .filter(profile -> Boolean.TRUE.equals(profile.getIsActive()))
                .toList();
    }
    
    @Override
    public boolean existsByUserId(Long userId) {
        return investorProfileRepository.existsByUserId(userId);
    }
    
    @Override
    @Transactional
    public void deleteProfile(Long userId) {
        Optional<InvestorProfile> existingProfile = investorProfileRepository.findByUserId(userId);
        if (existingProfile.isPresent()) {
            investorProfileRepository.delete(existingProfile.get());
        } else {
            throw new RuntimeException("Investor profile not found for user ID: " + userId);
        }
    }
}