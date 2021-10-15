package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.diet.EActivityLevel;

public class PalCompleteMetabolismStrategy implements CompleteMetabolismStrategy {
    private static final double THE_POWER_OF_FOOD = 0.1;

    private double calculateAdditionalPalFromTraining(int minutesActivePerWeek){
        return 0.3 / 150 * minutesActivePerWeek;
    }
    @Override
    public double calculateCompleteMetabolism(double basicMetabolism, EActivityLevel activityLevel,
                                              int minutesActivePerWeek) {

        return basicMetabolism * activityLevel.getPalFactor() + calculateAdditionalPalFromTraining(minutesActivePerWeek)
                + basicMetabolism * THE_POWER_OF_FOOD;
    }
}
