package com.wellbeeing.wellbeeing.domain.diet;

public enum EDietGoal {
    LOSE_WEIGHT(-300),
    KEEP_WEIGHT(0),
    GAIN_WEIGHT(300);

    private int changeInCalories;

    EDietGoal(int changeInCalories) {
    }

    public int getChangeInCalories() {
        return changeInCalories;
    }
}
