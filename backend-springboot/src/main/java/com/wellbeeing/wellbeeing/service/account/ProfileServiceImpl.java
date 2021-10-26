package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.account.ProfileCard;
import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.domain.account.TrainerProfile;
import com.wellbeeing.wellbeeing.repository.account.ProfileDAO;
import com.wellbeeing.wellbeeing.repository.account.TrainerDAO;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService {
    private ProfileDAO profileDAO;
    private UserDAO userDAO;
    private TrainerDAO trainerDAO;

    @Autowired
    public ProfileServiceImpl(@Qualifier("profileDAO") ProfileDAO profileDAO,
                              @Qualifier("userDAO") UserDAO userDAO,
                              @Qualifier("trainerDAO") TrainerDAO trainerDAO) {
        this.profileDAO = profileDAO;
        this.userDAO = userDAO;
        this.trainerDAO = trainerDAO;
    }

    @Override
    public Profile getProfileById(UUID profileId) throws NotFoundException {
        Profile profile = profileDAO.findById(profileId).orElse(null);
        if(profile != null)
            return profile;
        throw new NotFoundException("Profile with id: " + profileId + " not found");
    }
    @Override
    public List<TrainerProfile> getTrainersProfiles() {
        return trainerDAO.findAll();
    }
    @Override
    public Profile updateProfile(Profile profile, UUID profileId) throws NotFoundException {
        Profile actProfile = getProfileById(profileId);
        if(profile.getProfileImgPath() != null) actProfile.setProfileImgPath(profile.getProfileImgPath());
        if(profile.getFirstName() != null) actProfile.setFirstName(profile.getFirstName());
        if(profile.getLastName() != null) actProfile.setLastName(profile.getLastName());
        if(profile.getESex() != null) actProfile.setESex(profile.getESex());
        if(profile.getDescription() != null) actProfile.setDescription(profile.getDescription());
        if(profile.getBirthday() != null) actProfile.setBirthday(profile.getBirthday());
        actProfile.setProfileUser(profile.getProfileUser());
        actProfile.setProfileCard(profile.getProfileCard());
        actProfile.setProfileCard(profile.getProfileCard());
        actProfile.setTrainingPlans(profile.getTrainingPlans());
        actProfile.setTrainingPlans(profile.getTrainingPlans());
        actProfile.setActivityGoals(profile.getActivityGoals());
        actProfile.setDietReports(profile.getDietReports());
        profileDAO.save(actProfile);
        return actProfile;
    }
}
