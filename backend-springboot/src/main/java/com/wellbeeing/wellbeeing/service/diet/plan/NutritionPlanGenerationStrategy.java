package com.wellbeeing.wellbeeing.service.diet.plan;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.diet.nutrition_plan.NutritionPlan;
import com.wellbeeing.wellbeeing.domain.exception.NutritionPlanGenerationException;

import java.util.UUID;

public interface NutritionPlanGenerationStrategy {
    NutritionPlan generateNutritionPlan(Profile profile, UUID dietId) throws NutritionPlanGenerationException;
}
