package com.wellbeeing.wellbeeing.service.diet.plan;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.diet.NutritionPlan;
import com.wellbeeing.wellbeeing.domain.diet.NutritionPlanPosition;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.exception.NutritionPlanGenerationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("nutritionPlanService")
public interface NutritionPlanService {
    boolean deleteCreatedNutritionPlanFromProfile(UUID nutritionPlanId) throws NotFoundException;
    boolean deleteOwnedNutritionPlanFromProfile(UUID nutritionPlanId) throws NotFoundException;

    List<NutritionPlan> getAllCreatedByUserProfileNutritionPlans(UUID profileId) throws NotFoundException;
    List<NutritionPlan> getAllOwnedByUserProfileNutritionPlans(UUID profileId) throws NotFoundException;
    List<NutritionPlan> getAllCreatedByDieticianProfileNutritionPlans(UUID profileId) throws NotFoundException;

    NutritionPlan changeNutritionPlanMain(UUID nutritionPlanId, UUID profileId) throws NotFoundException;

    NutritionPlan getNutritionPlanById(UUID nutritionPlanId) throws NotFoundException;
    NutritionPlan generateNutritionPlanForProfile(UUID profileId) throws NotFoundException, NutritionPlanGenerationException;
    NutritionPlan addEmptyNutritionPlanToProfile(UUID profileId, String name) throws NotFoundException;
    NutritionPlan addEmptyNutritionPlanToDieticianProfile(UUID profileId, String name) throws NotFoundException;
    NutritionPlan addPositionToProfileNutritionPlan(NutritionPlanPosition position, UUID nutritionPlanId) throws NotFoundException;
    NutritionPlan deletePositionFromProfileNutritionPlan(UUID positionId, UUID nutritionPlanId) throws NotFoundException;

    NutritionPlan deleteOwnerFromNutritionPlan(UUID nutritionPlanId) throws NotFoundException;
    NutritionPlan addOwnerToNutritionPlan(UUID nutritionPlanId, Profile profile) throws NotFoundException;
}
