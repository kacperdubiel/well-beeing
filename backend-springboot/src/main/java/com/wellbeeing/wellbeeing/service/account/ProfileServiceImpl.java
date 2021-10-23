package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.repository.account.ProfileDAO;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService {
    private ProfileDAO profileDAO;
    private UserDAO userDAO;

    @Autowired
    public ProfileServiceImpl(@Qualifier("profileDAO") ProfileDAO profileDAO,
                              @Qualifier("userDAO") UserDAO userDAO) {
        this.profileDAO = profileDAO;
        this.userDAO = userDAO;
    }

    @Override
    public Profile getProfileById(UUID profileId) {
        return profileDAO.findById(profileId).orElse(null);
    }

    @Override
    public Profile updateProfile(Profile profile, UUID profileId) throws NotFoundException {
        Profile actProfile = profileDAO.findById(profileId).orElse(null);
        if (actProfile != null) {
            if(profile.getProfileImgPath() != null) actProfile.setProfileImgPath(profile.getProfileImgPath());
            if(profile.getFirstName() != null) actProfile.setFirstName(profile.getFirstName());
            if(profile.getLastName() != null) actProfile.setLastName(profile.getLastName());
            if(profile.getESex() != null) actProfile.setESex(profile.getESex());
            if(profile.getDescription() != null) actProfile.setDescription(profile.getDescription());
            if(profile.getBirthday() != null) actProfile.setBirthday(profile.getBirthday());
            if(profile.getProfileUser() != null) actProfile.setProfileUser(profile.getProfileUser());
            if(profile.getProfileCard() != null) actProfile.setProfileCard(profile.getProfileCard());
            if(profile.getProfileCard() != null) actProfile.setProfileCard(profile.getProfileCard());
            if(profile.getTrainingPlans() != null) actProfile.setTrainingPlans(profile.getTrainingPlans());
            if(profile.getTrainingPlans() != null) actProfile.setTrainingPlans(profile.getTrainingPlans());
            if(profile.getActivityGoals() != null) actProfile.setActivityGoals(profile.getActivityGoals());
            if(profile.getDietReports() != null) actProfile.setDietReports(profile.getDietReports());
            profileDAO.save(actProfile);
            return actProfile;
        }
        return null;
    }
}