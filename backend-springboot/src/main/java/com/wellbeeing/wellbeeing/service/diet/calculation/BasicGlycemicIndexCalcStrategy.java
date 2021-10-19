package com.wellbeeing.wellbeeing.service.diet.calculation;

import com.wellbeeing.wellbeeing.domain.account.ProfileCard;
import com.wellbeeing.wellbeeing.domain.diet.Ailment;
import com.wellbeeing.wellbeeing.domain.diet.type.EGlycemicIndexLevel;
import com.wellbeeing.wellbeeing.domain.diet.type.EMealType;

import java.util.HashMap;

public class BasicGlycemicIndexCalcStrategy implements GlycemicIndexCalcStrategy {
        private static int HIGH_ACTIVITY_MINUTES_PER_WEEK_LEVEL = 300;
    @Override
    public HashMap<EMealType, EGlycemicIndexLevel> countGlycemicIndexesForMeals(ProfileCard profileCard) {
        HashMap<EMealType, EGlycemicIndexLevel> glycIndexesForMeals = new HashMap<>();
        EGlycemicIndexLevel actualRecommendedGlycIndex = EGlycemicIndexLevel.ANY_RECOMMENDED;
        for(Ailment a : profileCard.getAilments()){
            if (a.isLowGlycemicIndexRecommended()) {
                actualRecommendedGlycIndex = EGlycemicIndexLevel.LOW;
                break;
            }
        }
        glycIndexesForMeals.put(EMealType.BREAKFAST, actualRecommendedGlycIndex);
        glycIndexesForMeals.put(EMealType.LUNCH, actualRecommendedGlycIndex);
        glycIndexesForMeals.put(EMealType.DINNER, actualRecommendedGlycIndex);
        glycIndexesForMeals.put(EMealType.SNACK, actualRecommendedGlycIndex);
        glycIndexesForMeals.put(EMealType.SUPPER, actualRecommendedGlycIndex);

        if(profileCard.getTrainingActivityTimePerWeek() > HIGH_ACTIVITY_MINUTES_PER_WEEK_LEVEL){
            glycIndexesForMeals.put(EMealType.DINNER, EGlycemicIndexLevel.HIGH);
        }
        return  glycIndexesForMeals;
    }
}
