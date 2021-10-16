package com.wellbeeing.wellbeeing.service.diet.calculation;

import com.wellbeeing.wellbeeing.domain.account.ProfileCard;

public class PalCompleteMetabolismStrategy implements CompleteMetabolismStrategy {
    private static final double THE_POWER_OF_FOOD = 0.1;

    private double calculateAdditionalPalFromTraining(int minutesActivePerWeek){
        return 0.3 / 150 * minutesActivePerWeek;
    }
    @Override
    public double calculateCompleteMetabolism(double basicMetabolism, ProfileCard profileCard) {

        return basicMetabolism * profileCard.getActivityLevel().getPalFactor() +
                calculateAdditionalPalFromTraining(profileCard.getTrainingActivityTimePerWeek())
                + basicMetabolism * THE_POWER_OF_FOOD;
    }
}
