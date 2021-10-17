package com.wellbeeing.wellbeeing.domain.diet;

public enum EMealType {
    BREAKFAST(0.25),
    LUNCH(0.1),
    DINNER(0.4),
    SNACK(0.1),
    SUPPER(0.15);

    private double suggestedDailyCaloriesPars;

    EMealType(double suggestedDailyCaloriesPart) {
        this.suggestedDailyCaloriesPars = suggestedDailyCaloriesPart;
    }

    public double getSuggestedDailyCaloriesPars() {
        return suggestedDailyCaloriesPars;
    }
}
