package com.wellbeeing.wellbeeing.domain.diet;

public enum EBMIResult {
    SEVERELY_UNDERWEIGHT(600, Integer.MIN_VALUE, 16),
    UNDERWEIGHT(300, 16, 18.5),
    HEALTHY(0, 18.5, 25),
    OVERWEIGHT(-300, 25, 30),
    OBESE(-600, 30, Integer.MAX_VALUE);

    private int suggestedChangeInCalories;
    private double lowerRange;
    private double higherRange;

    EBMIResult(int suggestedChangeInCalories, double lowerRange, double higherRange){

    }

    public int getSuggestedChangeInCalories() {
        return suggestedChangeInCalories;
    }

    public double getLowerRange() {
        return lowerRange;
    }

    public double getHigherRange() {
        return higherRange;
    }
}
