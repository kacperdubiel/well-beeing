package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.account.ProfileCard;
import com.wellbeeing.wellbeeing.repository.account.ProfileCardDAO;
import com.wellbeeing.wellbeeing.repository.account.ProfileDAO;
import com.wellbeeing.wellbeeing.service.diet.calculation.ProfileDietCalculationService;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("profileCardService")
public class ProfileCardServiceImpl implements ProfileCardService {

    private ProfileDietCalculationService profileDietCalculationService;
    private ProfileService profileService;
    private ProfileCardDAO profileCardDAO;

    @Autowired
    public ProfileCardServiceImpl(@Qualifier("profileDietCalculationService")
                                      ProfileDietCalculationService profileDietCalculationService,
                                  @Qualifier("profileCardDAO") ProfileCardDAO profileCardDAO,
                                  @Qualifier("profileService") ProfileService profileService){
        this.profileDietCalculationService = profileDietCalculationService;
        this.profileCardDAO = profileCardDAO;
        this.profileService = profileService;
    }

    @Override
    public ProfileCard updateProfileCardById(ProfileCard newProfileCard, UUID profileCardId) throws NotFoundException {
        ProfileCard profileCard = profileCardDAO.findById(profileCardId).orElse(null);
        if (profileCard != null) {
            newProfileCard.setId(profileCardId);
            newProfileCard.setDietCalculations(profileCard.getDietCalculations());
            newProfileCard.setProfile(profileCard.getProfile());
            profileCardDAO.save(newProfileCard);
            profileDietCalculationService.updateDietCalculationByProfileId(profileCard.getProfile().getId());
            return newProfileCard;
        }
        throw new NotFoundException("Profile card with id: " + profileCardId + " not found");
    }

    @Override
    public ProfileCard getProfileCardById(UUID profileCardId) throws NotFoundException {
        ProfileCard profileCard = profileCardDAO.findById(profileCardId).orElse(null);
        if(profileCard != null)
            return profileCard;
        else throw new NotFoundException("Profile card with id: " + profileCardId + " not found");
    }

    @Override
    public ProfileCard getProfileCardByProfileId(UUID profileId) throws NotFoundException {
        Profile actProfile =  profileService.getProfileById(profileId);
        return profileCardDAO.findById(actProfile.getProfileCard().getId()).orElse(null);
    }

    @Override
    public ProfileCard updateProfileCardByProfileId(ProfileCard profileCard, UUID profileId) throws NotFoundException {
        Profile actProfile =  profileService.getProfileById(profileId);
        return updateProfileCardById(profileCard, actProfile.getProfileCard().getId());
    }
}
