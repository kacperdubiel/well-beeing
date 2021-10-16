package com.wellbeeing.wellbeeing.service.diet.calculation;

import com.wellbeeing.wellbeeing.domain.diet.ProfileDietCalculation;

import java.util.UUID;

public interface ProfileDietCalculationService {
    ProfileDietCalculation calculateAllSuggestionsForProfileCard(UUID profileCardId);
    ProfileDietCalculation getProfileDietCalculationById(UUID profileDietCalculationId);
}
