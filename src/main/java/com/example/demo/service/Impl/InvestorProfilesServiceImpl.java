package com.example.demo.service.impl;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.repository.InvestorProfileRepository;
import com.example.demo.service.InvestorProfilesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class InvestorProfilesServiceImpl implements InvestorProfilesService {

    private final InvestorProfileRepository investorProfileRepository;

    public InvestorProfilesServiceImpl(InvestorProfileRepository investorProfileRepository) {
        this.investorProfileRepository = investorProfileRepository;
    }

    @Override
    public Optional<InvestorProfile> getInvestorProfileByInvestorId(String investorId) {
        return investorProfileRepository.findByInvestorId(investorId);
    }

    @Override
    public InvestorProfile saveInvestorProfile(InvestorProfile profile) {
        return investorProfileRepository.save(profile);
    }
}
