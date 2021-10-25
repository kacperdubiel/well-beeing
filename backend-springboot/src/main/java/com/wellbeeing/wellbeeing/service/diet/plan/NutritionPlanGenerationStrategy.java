package com.wellbeeing.wellbeeing.service.diet.plan;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.diet.NutritionPlan;
import com.wellbeeing.wellbeeing.domain.exception.NutritionPlanGenerationException;

public interface NutritionPlanGenerationStrategy {
    NutritionPlan generateNutritionPlan(Profile profile) throws NutritionPlanGenerationException;
}
