package com.example.demo.service.impl;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.repository.InvestorProfileRepository;
import com.example.demo.service.InvestorProfileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InvestorProfileServiceImpl implements InvestorProfileService {

    private final InvestorProfileRepository investorProfileRepository;

    public InvestorProfileServiceImpl(InvestorProfileRepository investorProfileRepository) {
        this.investorProfileRepository = investorProfileRepository;
    }

    @Override
    public InvestorProfile createProfile(InvestorProfile profile) {
        return investorProfileRepository.save(profile);
    }

    @Override
    public InvestorProfile getProfileById(Long id) {
        return investorProfileRepository.findById(id).orElse(null);
    }

    @Override
    public InvestorProfile getProfileByUserId(Long userId) {
        return investorProfileRepository.findByUserId(userId).orElse(null);
    }

    @Override
    public List<InvestorProfile> getAllProfiles() {
        return investorProfileRepository.findAll();
    }

    @Override
    public InvestorProfile updateProfile(Long id, InvestorProfile profile) {
        InvestorProfile existing = getProfileById(id);
        if (existing != null) {
            existing.setUserId(profile.getUserId());
            existing.setRiskTolerance(profile.getRiskTolerance());
            existing.setInvestmentHorizon(profile.getInvestmentHorizon());
            existing.setInitialInvestment(profile.getInitialInvestment());
            existing.setCurrentInvestment(profile.getCurrentInvestment());
            existing.setActive(profile.isActive());
            return investorProfileRepository.save(existing);
        }
        return null;
    }

    @Override
    public InvestorProfile updateProfileStatus(Long id, boolean active) {
        InvestorProfile profile = getProfileById(id);
        if (profile != null) {
            profile.setActive(active);
            return investorProfileRepository.save(profile);
        }
        return null;
    }

    @Override
    public void deleteProfile(Long id) {
        investorProfileRepository.deleteById(id);
    }

    @Override
    public boolean profileExistsForUser(Long userId) {
        return investorProfileRepository.existsByUserId(userId);
    }
}