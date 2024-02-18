package com.ecohome.util;

public class EnergyCalculator {

    private static final double COST_PER_KWH = 0.12; // $0.12 per kWh

    public static double calculateCost(double consumptionKWh) {
        return consumptionKWh * COST_PER_KWH;
    }

    public static double calculateSavings(double previousConsumption, double currentConsumption) {
        double savings = previousConsumption - currentConsumption;
        return savings > 0 ? calculateCost(savings) : 0;
    }

    public static double convertWattHoursToKWh(double wattHours) {
        return wattHours / 1000.0;
    }

    public static double convertKWhToWattHours(double kWh) {
        return kWh * 1000.0;
    }

    public static String getEfficiencyRating(double consumption, double threshold) {
        if (consumption < threshold * 0.7) {
            return "Excellent";
        } else if (consumption < threshold * 0.85) {
            return "Good";
        } else if (consumption < threshold) {
            return "Fair";
        } else {
            return "Poor";
        }
    }
}
