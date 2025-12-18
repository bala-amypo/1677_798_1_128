import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class RebalancingAlertController {

    private final RebalancingAlertService rebalancingAlertService;

    public RebalancingAlertController(RebalancingAlertService rebalancingAlertService) {
        this.rebalancingAlertService = rebalancingAlertService;
    }

    @PostMapping
    public ResponseEntity<RebalancingAlertDto> createAlert(@RequestBody RebalancingAlertDto request) {
        RebalancingAlertDto created = rebalancingAlertService.createAlert(request);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<RebalancingAlertDto> resolveAlert(@PathVariable Long id) {
        RebalancingAlertDto resolved = rebalancingAlertService.resolveAlert(id);
        return ResponseEntity.ok(resolved);
    }

    @GetMapping("/investor/{investorId}")
    public ResponseEntity<List<RebalancingAlertDto>> getAlertsByInvestor(@PathVariable Long investorId) {
        List<RebalancingAlertDto> alerts = rebalancingAlertService.getAlertsByInvestor(investorId);
        return ResponseEntity.ok(alerts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RebalancingAlertDto> getAlert(@PathVariable Long id) {
        RebalancingAlertDto alert = rebalancingAlertService.getAlert(id);
        return ResponseEntity.ok(alert);
    }

    @GetMapping
    public ResponseEntity<List<RebalancingAlertDto>> listAll() {
        List<RebalancingAlertDto> alerts = rebalancingAlertService.getAllAlerts();
        return ResponseEntity.ok(alerts);
    }
}
