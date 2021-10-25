package com.wellbeeing.wellbeeing.service.diet.plan;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.diet.DishMealType;
import com.wellbeeing.wellbeeing.domain.diet.NutritionPlan;
import com.wellbeeing.wellbeeing.domain.diet.NutritionPlanPosition;
import com.wellbeeing.wellbeeing.domain.diet.calculation.DietCalcMealSuggestions;
import com.wellbeeing.wellbeeing.domain.diet.calculation.ProfileDietCalculation;
import com.wellbeeing.wellbeeing.domain.diet.type.EMealType;
import com.wellbeeing.wellbeeing.domain.diet.type.EWeekDay;
import com.wellbeeing.wellbeeing.domain.exception.NutritionPlanGenerationException;
import com.wellbeeing.wellbeeing.repository.diet.DishMealTypeDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class SimpleNutritionPlanGenerationStrategy implements NutritionPlanGenerationStrategy {

    @Autowired
    private DishMealTypeDAO dishMealTypeDAO;
    private int CALORIES_TOLERANCE = 50;

    @Override
    public NutritionPlan generateNutritionPlan(Profile profile) throws NutritionPlanGenerationException {
        NutritionPlan actualNutritionPlan = NutritionPlan.builder().build();
        for(EWeekDay weekDay : EWeekDay.values()){
            for(EMealType mealType : EMealType.values()){
                ProfileDietCalculation calc = profile.getProfileCard().getDietCalculations();
                DietCalcMealSuggestions sugg = calc.getDietCalcMealSuggestions().stream()
                        .filter(s -> s.getMealType().equals(mealType))
                        .findFirst()
                        .orElse(null);
                DishMealType dishMealType = dishMealTypeDAO.findAllByMealType(mealType).stream()
                        .filter(dmt -> {
                            assert sugg != null;
                            return Math.abs(dmt.getDish().getDerivedCalories() - sugg.getNumberOfCalories()) < CALORIES_TOLERANCE;
                        }).findAny().orElse(null);
                if(dishMealType == null){
                    throw new NutritionPlanGenerationException
                            ("Not enough data to generate full nutrition plan, cannot find meal for : " + weekDay.toString() + " , " + mealType.toString());
                }
                NutritionPlanPosition actualNutritionPlanPosition = NutritionPlanPosition.builder()
                        .dish(dishMealType.getDish())
                        .mealType(mealType)
                        .weekDay(weekDay)
                        .nutritionPlan(actualNutritionPlan)
                        .build();
                actualNutritionPlan.getNutritionPlanPositions().add(actualNutritionPlanPosition);
            }
        }
        return actualNutritionPlan;
    }
}
