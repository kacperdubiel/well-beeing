package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.account.ProfileCard;
import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.repository.account.ProfileDAO;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import javassist.NotFoundException;
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
    public Profile getProfileById(UUID profileId) throws NotFoundException {
        Profile actProfile = profileDAO.findById(profileId).orElse(null);
        if (actProfile != null)
            return actProfile;
        else throw new NotFoundException("Profile not found");
    }

    @Override
    public Profile updateProfile(Profile profile, UUID profileId) throws NotFoundException {
        Profile actProfile = profileDAO.findById(profileId).orElse(null);
        if (actProfile != null) {
            profile.setId(profileId);
            profile.setProfileCard(actProfile.getProfileCard());
            profile.setProfileUser(actProfile.getProfileUser());
            profileDAO.save(profile);
            return profile;
        }
        throw new NotFoundException("Profile not found");
    }
}