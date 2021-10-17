package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.account.ProfileCard;
import com.wellbeeing.wellbeeing.domain.diet.ProfileDietCalculation;
import javassist.NotFoundException;

import java.util.UUID;

public interface ProfileService {
    Profile getProfileById(UUID profileId);
    Profile updateProfile(Profile profile, UUID profileId) throws NotFoundException;
    ProfileCard getProfileCardByProfileId(UUID profileId) throws NotFoundException;
    ProfileCard updateProfileCardByProfileId(ProfileCard profileCard, UUID profileId) throws NotFoundException;
    ProfileDietCalculation getProfileDietCalculationByProfileId(UUID profileID) throws NotFoundException;
}
