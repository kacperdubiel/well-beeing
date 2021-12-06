package com.wellbeeing.wellbeeing.service.diet.plan;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.diet.*;
import com.wellbeeing.wellbeeing.domain.diet.calculation.DietCalcMealSuggestions;
import com.wellbeeing.wellbeeing.domain.diet.calculation.ProfileDietCalculation;
import com.wellbeeing.wellbeeing.domain.diet.dish.DishMealType;
import com.wellbeeing.wellbeeing.domain.diet.nutrition_plan.NutritionPlan;
import com.wellbeeing.wellbeeing.domain.diet.nutrition_plan.NutritionPlanPosition;
import com.wellbeeing.wellbeeing.domain.diet.type.EMealType;
import com.wellbeeing.wellbeeing.domain.diet.type.EWeekDay;
import com.wellbeeing.wellbeeing.domain.exception.NutritionPlanGenerationException;
import com.wellbeeing.wellbeeing.repository.diet.DietDAO;
import com.wellbeeing.wellbeeing.repository.diet.dish.DishMealTypeDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SimpleNutritionPlanGenerationStrategy implements NutritionPlanGenerationStrategy {
    private final DishMealTypeDAO dishMealTypeDAO;
    private final DietDAO dietDAO;
    private final static int CALORIES_TOLERANCE = 100;

    public SimpleNutritionPlanGenerationStrategy(DishMealTypeDAO dishMealTypeDAO,
                                                 DietDAO dietDAO){
        this.dishMealTypeDAO = dishMealTypeDAO;
        this.dietDAO = dietDAO;
    }

    @Override
    public NutritionPlan generateNutritionPlan(Profile profile, UUID dietId) throws NutritionPlanGenerationException {
        NutritionPlan actualNutritionPlan = NutritionPlan.builder().nutritionPlanPositions(new ArrayList<>()).build();
        for(EWeekDay weekDay : EWeekDay.values()){
            for(EMealType mealType : EMealType.values()){
                ProfileDietCalculation calc = profile.getProfileCard().getDietCalculations();
                Diet diet =  null;
                List<NutritionLabel> dietLabels = new ArrayList<>();

                if(dietId != null){
                    diet = dietDAO.findById(dietId).orElse(null);
                    if(diet != null)
                        dietLabels = diet.getAllowedNutritionLabels();
                }

                DietCalcMealSuggestions sugg = calc.getDietCalcMealSuggestions().stream()
                        .filter(s -> s.getMealType().equals(mealType))
                        .findFirst()
                        .orElse(null);
                DishMealType dishMealType = null;
                if(sugg != null) {
                    List<NutritionLabel> finalDietLabels = dietLabels;
                    if(finalDietLabels.size() != 0){
                        dishMealType = dishMealTypeDAO.findAllByMealType(mealType).stream()
                                .filter(dmt ->
                                        (Math.abs(dmt.getDish().getDerivedNutritionalValues().getDerivedCalories() - sugg.getNumberOfCalories()) < CALORIES_TOLERANCE) && (dmt.getDish().getAllowedForNutritionLabels().stream().anyMatch(finalDietLabels::contains)))
                                .findAny()
                                .orElse(null);
                    }
                    else{
                        dishMealType = dishMealTypeDAO.findAllByMealType(mealType).stream()
                                .filter(dmt ->
                                        (Math.abs(dmt.getDish().getDerivedNutritionalValues().getDerivedCalories() - sugg.getNumberOfCalories()) < CALORIES_TOLERANCE))
                                .findAny()
                                .orElse(null);
                    }
                }
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
