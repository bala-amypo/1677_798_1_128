package com.example.demo.service.impl;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.InvestorProfileRepository;
import com.example.demo.service.InvestorProfileService;
import org.springframework.stereotype.Service;

@Service
public class InvestorProfileServiceImpl
        implements InvestorProfileService {

    private final InvestorProfileRepository repository;

    public InvestorProfileServiceImpl(
            InvestorProfileRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public InvestorProfile createProfile(InvestorProfile profile) {
        return repository.save(profile);
    }

    @Override
    public InvestorProfile getByInvestorId(String investorId) {
        return repository.findByInvestorId(investorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Investor not found: " + investorId
                        ));
    }
}
