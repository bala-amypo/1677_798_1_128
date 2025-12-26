// src/main/java/com/example/demo/util/AllocationUtils.java
package com.example.demo.util;

import java.util.Map;

public class AllocationUtils {

    private AllocationUtils() {
    }

    public static double sum(Map<?, Double> map) {
        return map.values().stream().mapToDouble(Double::doubleValue).sum();
    }
}
