package com.example.demo.util;

public class AllocationUtils {

    public static double safePercentage(double value, double total) {
        if (total <= 0) {
            return 0.0;
        }
        return (value / total) * 100.0;
    }
}
