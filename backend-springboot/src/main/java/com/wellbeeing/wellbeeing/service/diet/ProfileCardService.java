package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.account.ProfileCard;
import com.wellbeeing.wellbeeing.domain.diet.ProfileDietCalculation;

import java.util.UUID;

public interface ProfileCardService {
    void updateDietCalculationsForProfileCard(UUID profileCardId);
    ProfileCard updateProfileCard(ProfileCard newProfileCard, UUID profileCardId);
    ProfileCard addProfileCard(ProfileCard newProfileCard);
    ProfileCard getProfileCardById(UUID profileCardId);
    ProfileDietCalculation getProfileDietCalculationByProfileCardId(UUID profileCardId);
}
