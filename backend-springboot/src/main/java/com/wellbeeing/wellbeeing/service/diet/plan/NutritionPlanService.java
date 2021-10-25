package com.wellbeeing.wellbeeing.service.diet.plan;

import com.wellbeeing.wellbeeing.domain.diet.NutritionPlan;
import com.wellbeeing.wellbeeing.domain.diet.NutritionPlanPosition;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("nutritionPlanService")
public interface NutritionPlanService {
    boolean deleteNutritionPlanFromProfile(UUID nutritionPlanId, UUID profileId);
    List<NutritionPlan> getAllProfileNutritionPlans(UUID profileId);
    NutritionPlan generateNutritionPlanForProfile(UUID profileId);
    NutritionPlan addNutritionPlanToProfile(NutritionPlan nutritionPlan, UUID profileId);
    NutritionPlanPosition addPositionToProfileNutritionPlan(NutritionPlanPosition position, UUID nutritionPlanId);
    NutritionPlanPosition updatePositionInProfileNutritionPlan(NutritionPlanPosition position, UUID positionId);
    NutritionPlanPosition deletePositionFromProfileNutritionPlan(UUID positionId);
}
