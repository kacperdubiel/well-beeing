package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.account.ProfileCard;
import com.wellbeeing.wellbeeing.domain.diet.ProfileDietCalculation;
import com.wellbeeing.wellbeeing.repository.account.ProfileDAO;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService {

    private ProfileCardService profileCardService;
    private ProfileDAO profileDAO;

    @Autowired
    public ProfileServiceImpl(@Qualifier("profileCardService") ProfileCardService profileCardService,
                              @Qualifier("profileDAO") ProfileDAO profileDAO){
        this.profileCardService = profileCardService;
        this.profileDAO = profileDAO;
    }

    @Override
    public Profile getProfileById(UUID profileId) {
        return profileDAO.findById(profileId).orElse(null);
    }

    @Override
    public Profile updateProfile(Profile profile, UUID profileId) throws NotFoundException {
        Profile actProfile = profileDAO.findById(profileId).orElse(null);
        if(actProfile != null) {
            profile.setId(profileId);
            profileDAO.save(profile);
            return profile;
        }
        throw new NotFoundException("Profile not found");
    }

    @Override
    public ProfileCard getProfileCardByProfileId(UUID profileId) throws NotFoundException {
        Profile actProfile = profileDAO.findById(profileId).orElse(null);
        if(actProfile != null)
            return profileCardService.getProfileCardById(actProfile.getProfileCard().getId());
        throw new NotFoundException("Profile not found");
    }

    @Override
    public ProfileCard updateProfileCardByProfileId(ProfileCard profileCard, UUID profileId) throws NotFoundException {
        Profile actProfile = profileDAO.findById(profileId).orElse(null);
        if(actProfile != null)
            return profileCardService.updateProfileCard(profileCard, actProfile.getProfileCard().getId());
        throw new NotFoundException("Profile not found");
    }

    @Override
    public ProfileDietCalculation getProfileDietCalculationByProfileId(UUID profileId) throws NotFoundException {
        Profile actProfile = profileDAO.findById(profileId).orElse(null);
        if(actProfile != null)
            return profileCardService.getProfileDietCalculationByProfileCardId(actProfile.getProfileCard().getId());
        throw new NotFoundException("Profile not found");
    }
}
