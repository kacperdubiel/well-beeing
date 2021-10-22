package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.diet.ProfileDietCalculation;

import java.util.UUID;

public interface ProfileDietCalculationServiceApi {
    ProfileDietCalculation calculateAllSuggestionsForCard(UUID profileCardId);
    ProfileDietCalculation getProfileDietCalculationByCard(UUID profileCardId);
    ProfileDietCalculation getProfileDietCalculation(UUID profileDietCalculationId);
}
