package com.wellbeeing.wellbeeing.domain.sport;

public enum EWorkoutStrategy {
    ONLY_WORKOUT(0.9, 0.1),
    MOSTLY_WORKOUT(0.75, 0.25),
    EVEN(0.5, 0.5),
    MOSTLY_DIET(0.25, 0.75),
    ONLY_DIET(0.1, 0.9);

    private final double workoutWeight;
    private final double dietWeight;

    EWorkoutStrategy(double workoutWeight, double dietWeight) {
        this.workoutWeight = workoutWeight;
        this.dietWeight = dietWeight;
    }

    public double getWorkoutWeight() {
        return workoutWeight;
    }

    public double getDietWeight() {
        return dietWeight;
    }
}
