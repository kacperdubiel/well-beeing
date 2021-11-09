package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.ProfileCard;
import com.wellbeeing.wellbeeing.domain.diet.calculation.ProfileDietCalculation;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;

import java.util.UUID;

public interface ProfileCardService {
    ProfileCard updateProfileCardById(ProfileCard newProfileCard, UUID profileCardId) throws NotFoundException;
    ProfileCard getProfileCardById(UUID profileCardId) throws NotFoundException;
    ProfileCard getProfileCardByProfileId(UUID profileId) throws NotFoundException;
    ProfileCard updateProfileCardByProfileId(ProfileCard profileCard, UUID profileId) throws NotFoundException;
}
