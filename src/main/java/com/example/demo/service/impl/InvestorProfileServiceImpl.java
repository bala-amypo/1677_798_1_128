@Service
public class InvestorProfileServiceImpl implements InvestorProfileService {
    private final InvestorProfileRepository repo;
    public InvestorProfileServiceImpl(InvestorProfileRepository repo) { this.repo = repo; }

    public InvestorProfile createInvestor(InvestorProfile inv) { return repo.save(inv); }
    public InvestorProfile getInvestorById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Investor not found: " + id));
    }
    public List<InvestorProfile> getAllInvestors() { return repo.findAll(); }
    public Optional<InvestorProfile> findByInvestorId(String iid) { return repo.findByInvestorId(iid); }
    public InvestorProfile updateInvestorStatus(Long id, Boolean active) {
        InvestorProfile inv = getInvestorById(id);
        inv.setActive(active);
        return repo.save(inv);
    }
}