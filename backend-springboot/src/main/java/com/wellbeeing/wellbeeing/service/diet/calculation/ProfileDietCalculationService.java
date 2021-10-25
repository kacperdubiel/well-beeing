package com.wellbeeing.wellbeeing.service.diet.calculation;

import com.wellbeeing.wellbeeing.domain.diet.calculation.ProfileDietCalculation;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;

import java.util.UUID;

public interface ProfileDietCalculationService {
    ProfileDietCalculation calculateAllSuggestionsByProfileId(UUID profileId) throws NotFoundException;
    ProfileDietCalculation getDietCalculationById(UUID dietCalculationId) throws NotFoundException;
    //ProfileDietCalculation getDietCalculationByProfileCardId(UUID profileCardId) throws NotFoundException;
    //ProfileDietCalculation updateDietCalculationByProfileCardId(UUID profileCardId) throws NotFoundException;
    ProfileDietCalculation getDietCalculationByProfileId(UUID profileDietCalculationId) throws NotFoundException;
    ProfileDietCalculation updateDietCalculationByProfileId(UUID profileId) throws NotFoundException;
}
