@Service
public class RebalancingAlertServiceImpl implements RebalancingAlertService {
    private final RebalancingAlertRecordRepository repo;
    public RebalancingAlertServiceImpl(RebalancingAlertRecordRepository repo) { this.repo = repo; }

    public RebalancingAlertRecord createAlert(RebalancingAlertRecord alert) {
        if (alert.getCurrentPercentage() <= alert.getTargetPercentage()) {
            throw new IllegalArgumentException("Condition violation: currentPercentage > targetPercentage required");
        }
        return repo.save(alert);
    }

    public RebalancingAlertRecord resolveAlert(Long id) {
        RebalancingAlertRecord alert = repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Alert not found: " + id));
        alert.setResolved(true);
        return repo.save(alert);
    }
    public List<RebalancingAlertRecord> getAlertsByInvestor(Long id) { return repo.findByInvestorId(id); }
}