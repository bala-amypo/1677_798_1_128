public interface InvestorProfileService {

    InvestorProfile createInvestor(InvestorProfile investor);

    InvestorProfile getInvestorById(Long id);  // throws if not found

    InvestorProfile findByInvestorId(String investorId);

    List<InvestorProfile> getAllInvestors();

    InvestorProfile updateInvestorStatus(Long id, boolean active);
}
