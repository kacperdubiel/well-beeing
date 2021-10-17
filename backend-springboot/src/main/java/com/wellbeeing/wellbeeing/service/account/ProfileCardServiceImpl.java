package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.ProfileCard;
import com.wellbeeing.wellbeeing.domain.diet.ProfileDietCalculation;
import com.wellbeeing.wellbeeing.repository.account.ProfileCardDAO;
import com.wellbeeing.wellbeeing.repository.diet.ProfileDietCalculationDAO;
import com.wellbeeing.wellbeeing.service.diet.calculation.ProfileDietCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("profileCardService")
public class ProfileCardServiceImpl implements ProfileCardService {

    private ProfileDietCalculationService profileDietCalculationService;
    private ProfileDietCalculationDAO profileDietCalculationDAO;
    private ProfileCardDAO profileCardDAO;

    @Autowired
    public ProfileCardServiceImpl(@Qualifier("profileDietCalculationService")
                                      ProfileDietCalculationService profileDietCalculationService,
                                  @Qualifier("profileDietCalculationDAO") ProfileDietCalculationDAO profileDietCalculationDAO,
                                  @Qualifier("profileCardDAO") ProfileCardDAO profileCardDAO
                              ){
        this.profileDietCalculationService = profileDietCalculationService;
        this.profileDietCalculationDAO = profileDietCalculationDAO;
        this.profileCardDAO = profileCardDAO;
    }

    @Override
    public void updateDietCalculationsForProfileCard(UUID profileCardId) {
        ProfileCard profileCard = profileCardDAO.findById(profileCardId).orElse(null);
        if(profileCard != null){
            ProfileDietCalculation calcForProfileCard =
                    profileDietCalculationService.calculateAllSuggestionsForProfileCard(profileCardId);
            profileDietCalculationDAO.save(calcForProfileCard);
            profileCard.setDietCalculations(calcForProfileCard);
            profileCardDAO.save(profileCard);
        }
    }


    @Override
    public ProfileCard updateProfileCard(ProfileCard newProfileCard, UUID profileCardId) {
        ProfileCard profileCard = profileCardDAO.findById(profileCardId).orElse(null);
        if (profileCard != null) {
            newProfileCard.setId(profileCardId);
            profileCardDAO.save(newProfileCard);
            updateDietCalculationsForProfileCard(profileCardId);
            return newProfileCard;
        }
        return null;
    }

    @Override
    public ProfileCard getProfileCardById(UUID profileCardId) {
        return profileCardDAO.findById(profileCardId).orElse(null);
    }

    @Override
    public ProfileDietCalculation getProfileDietCalculationByProfileCardId(UUID profileCardId) {
        ProfileCard card = profileCardDAO.findById(profileCardId).orElse(null);
        if(card != null)
            return card.getDietCalculations();
        return null;
    }
}
