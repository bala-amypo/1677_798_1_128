import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/holdings")
public class HoldingRecordController {

    private final HoldingRecordService holdingRecordService;

    public HoldingRecordController(HoldingRecordService holdingRecordService) {
        this.holdingRecordService = holdingRecordService;
    }

    @PostMapping
    public ResponseEntity<HoldingRecordDto> recordHolding(@RequestBody HoldingRecordDto request) {
        HoldingRecordDto saved = holdingRecordService.recordHolding(request);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/investor/{investorId}")
    public ResponseEntity<List<HoldingRecordDto>> getHoldingsByInvestor(@PathVariable Long investorId) {
        List<HoldingRecordDto> holdings = holdingRecordService.getHoldingsByInvestor(investorId);
        return ResponseEntity.ok(holdings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HoldingRecordDto> getHolding(@PathVariable Long id) {
        HoldingRecordDto holding = holdingRecordService.getHolding(id);
        return ResponseEntity.ok(holding);
    }

    @GetMapping
    public ResponseEntity<List<HoldingRecordDto>> listAll() {
        List<HoldingRecordDto> holdings = holdingRecordService.getAllHoldings();
        return ResponseEntity.ok(holdings);
    }
}
