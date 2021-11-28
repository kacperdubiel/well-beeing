package com.wellbeeing.wellbeeing.domain.sport;

public enum EGoalType {
    LOSE_WEIGHT(2500, 0.7),
    GAIN_WEIGHT(500, 0.3),
    MAINTAIN_WEIGHT(0, 0.5),
    EXTEND_LUNGS_CAPACITY(0, 1.0),
    BUILD_MUSCLE(1200, 0.7);

    private final int changeInCaloriesBurned;
    private final double intensity;

    EGoalType(int changeInCaloriesBurned, double intensity) {
        this.changeInCaloriesBurned = changeInCaloriesBurned;
        this.intensity = intensity;
    }

    public int getChangeInCaloriesBurned() {
        return changeInCaloriesBurned;
    }

    public double getIntensity() {
        return intensity;
    }
}
