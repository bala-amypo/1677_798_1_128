package com.example.demo.service;

import com.example.demo.entity.InvestorProfile;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface InvestorProfileService {
    InvestorProfile createProfile(Long userId, String riskTolerance, 
                                 String investmentHorizon, BigDecimal initialInvestment);
    
    Optional<InvestorProfile> getProfileByUserId(Long userId);
    
    InvestorProfile updateProfile(Long userId, String riskTolerance, 
                                 String investmentHorizon, BigDecimal currentInvestment);
    
    void updateInvestorStatus(Long userId, boolean isActive);
    
    void updateCurrentInvestment(Long userId, BigDecimal currentInvestment);
    
    List<InvestorProfile> getAllActiveProfiles();
    
    boolean existsByUserId(Long userId);
    
    void deleteProfile(Long userId);
}