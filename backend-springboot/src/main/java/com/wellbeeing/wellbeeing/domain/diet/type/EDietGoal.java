package com.wellbeeing.wellbeeing.domain.diet.type;

public enum EDietGoal {
    FAST_LOSE_WEIGHT(-500, 1),
    LOSE_WEIGHT(-300, 0.9),
    KEEP_WEIGHT(0, 0),
    GAIN_WEIGHT(300, 0),
    FAST_GAIN_WEIGHT(500, 0),
    GAIN_MUSCLES(300, 0.8);

    private int changeInCalories;
    private double changeInProteinsGramsPerKilogramWeight;

    EDietGoal(int changeInCalories, double changeInProteinsGramsPerKilogramWeight) {
        this.changeInCalories = changeInCalories;
        this.changeInProteinsGramsPerKilogramWeight = changeInProteinsGramsPerKilogramWeight;
    }

    public int getChangeInCalories() {
        return changeInCalories;
    }

    public double getChangeInProteinsGramsPerKilogramWeight() {
        return changeInProteinsGramsPerKilogramWeight;
    }
}
