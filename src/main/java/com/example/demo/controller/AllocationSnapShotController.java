import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/snapshots")
public class AllocationSnapshotController {

    private final AllocationSnapshotService allocationSnapshotService;

    public AllocationSnapshotController(AllocationSnapshotService allocationSnapshotService) {
        this.allocationSnapshotService = allocationSnapshotService;
    }

    @PostMapping("/compute/{investorId}")
    public ResponseEntity<AllocationSnapshotDto> computeSnapshot(@PathVariable Long investorId) {
        AllocationSnapshotDto snapshot = allocationSnapshotService.computeSnapshot(investorId);
        return ResponseEntity.ok(snapshot);
    }

    @GetMapping("/investor/{investorId}")
    public ResponseEntity<List<AllocationSnapshotDto>> getSnapshotsByInvestor(@PathVariable Long investorId) {
        List<AllocationSnapshotDto> snapshots = allocationSnapshotService.getSnapshotsByInvestor(investorId);
        return ResponseEntity.ok(snapshots);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AllocationSnapshotDto> getSnapshot(@PathVariable Long id) {
        AllocationSnapshotDto snapshot = allocationSnapshotService.getSnapshot(id);
        return ResponseEntity.ok(snapshot);
    }

    @GetMapping
    public ResponseEntity<List<AllocationSnapshotDto>> listAll() {
        List<AllocationSnapshotDto> snapshots = allocationSnapshotService.getAllSnapshots();
        return ResponseEntity.ok(snapshots);
    }
}
