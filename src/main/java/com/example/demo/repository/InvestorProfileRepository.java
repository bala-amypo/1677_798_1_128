package com.example.demo.repository;

import com.example.demo.entity.*;
import com.example.demo.entity.enums.AssetClassType;
import org.springframework.data.jpa.repository.*;
import java.util.*;

public interface InvestorProfileRepository extends JpaRepository<InvestorProfile, Long> {
    Optional<InvestorProfile> findByInvestorId(String investorId);
}
