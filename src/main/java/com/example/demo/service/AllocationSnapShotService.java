import java.time.LocalDateTime;
import java.util.Map;

public class AllocationSnapshot {
    private Long id;
    private Long investorId;
    private LocalDateTime createdAt;
    // assetClass -> percentage of total holdings at this snapshot
    private Map<String, Double> allocationPercentages;

    // constructors, getters, setters
}

public class AllocationAlert {
    private Long id;
    private Long investorId;
    private String assetClass;
    private double actualPercentage;
    private double rulePercentage;
    private double thresholdExceeded; // e.g. difference
    private LocalDateTime createdAt;

    // constructors, getters, setters
}
