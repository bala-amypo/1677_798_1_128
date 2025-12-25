package com.example.demo.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AllocationUtils {
    public static double calculatePercentage(double value, double total) {
        if (total == 0) return 0.0;
        BigDecimal bd = new BigDecimal((value / total) * 100);
        return bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}