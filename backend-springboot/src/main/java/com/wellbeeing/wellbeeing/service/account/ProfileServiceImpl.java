package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.ESex;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.account.TrainerProfile;
import com.wellbeeing.wellbeeing.domain.social.ENutritionTag;
import com.wellbeeing.wellbeeing.domain.social.ESportTag;
import com.wellbeeing.wellbeeing.repository.account.ProfileDAO;
import com.wellbeeing.wellbeeing.repository.account.TrainerDAO;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
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
        Profile actProfile = profileDAO.findById(profileId).orElse(null);
        if (actProfile != null)
            return actProfile;
        else throw new NotFoundException("Profile not found");
    }
    @Override
    public List<TrainerProfile> getTrainersProfiles() {
        return trainerDAO.findAll();
    }
    @Override
    public Profile partialUpdateProfile(UUID profileId, Map<String, Object> fields) throws NotFoundException {

        Profile actProfile = profileDAO.findById(profileId).orElse(null);

        if (actProfile == null || fields == null || fields.isEmpty()) {
            throw new NotFoundException("Bad request!");
        }
        fields.remove("id");
        fields.remove("profileUser");
        fields.remove("profileCard");
        fields.remove("settings");
        fields.remove("measures");
        fields.remove("profileConnections");
        fields.remove("trainingPlans");
        fields.remove("activityGoals");
        fields.remove("roles");
        fields.remove("profileLikes");
        fields.remove("profileComments");
        fields.remove("profilePosts");
        fields.remove("profileGivenOpinions");
        fields.remove("profileReceivedOpinions");
        fields.remove("profileRoleRequests");

        fields.forEach((k, v) -> {
            // use reflection to get field k on object and set it to value v
            // Change Claim.class to whatver your object is: Object.class
            Field field = ReflectionUtils.findField(Profile.class, k); // find field in the object class

            if (field != null) {
                field.setAccessible(true);
                if (field.getType() == ESex.class)
                    v = ESex.valueOf((String) v);
                if (field.getType() == ESportTag.class)
                    v = ESportTag.valueOf((String) v);
                if (field.getType() == ENutritionTag.class)
                    v = ENutritionTag.valueOf((String) v);
                ReflectionUtils.setField(field, actProfile, v);
            }
        });

        profileDAO.save(actProfile);
        return actProfile;

    }
}
