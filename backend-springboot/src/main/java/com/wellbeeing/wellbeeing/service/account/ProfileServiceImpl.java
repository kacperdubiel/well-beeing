package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.*;
import com.wellbeeing.wellbeeing.domain.social.ENutritionTag;
import com.wellbeeing.wellbeeing.domain.social.ESportTag;
import com.wellbeeing.wellbeeing.repository.account.ProfileDAO;
import com.wellbeeing.wellbeeing.repository.account.TrainerDAO;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.repository.account.DoctorProfileDAO;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService {
    private ProfileDAO profileDAO;
    private UserDAO userDAO;
    private TrainerDAO trainerDAO;
    private DoctorProfileDAO doctorProfileDAO;
    private DoctorSpecializationService doctorSpecService;

    @Autowired
    public ProfileServiceImpl(@Qualifier("profileDAO") ProfileDAO profileDAO,
                              @Qualifier("userDAO") UserDAO userDAO,
                              @Qualifier("trainerDAO") TrainerDAO trainerDAO,
                              @Qualifier("doctorProfileDAO") DoctorProfileDAO doctorProfileDAO,
                              @Qualifier("doctorSpecializationService") DoctorSpecializationService doctorSpecService) {
        this.profileDAO = profileDAO;
        this.userDAO = userDAO;
        this.trainerDAO = trainerDAO;
        this.doctorProfileDAO = doctorProfileDAO;
        this.doctorSpecService = doctorSpecService;
    }

    @Override
    public Profile getProfileById(UUID profileId) throws NotFoundException {
        Profile actProfile = profileDAO.findById(profileId).orElse(null);
        if (actProfile != null)
            return actProfile;
        else throw new NotFoundException("Profile not found");
    }

    @Override
    public DoctorProfile getDoctorProfileById(UUID doctorProfileId) throws NotFoundException {
        DoctorProfile doctorProfile = doctorProfileDAO.findById(doctorProfileId).orElse(null);
        if(doctorProfile == null) {
            throw new NotFoundException("Doctor profile with id: " + doctorProfileId + " not found");
        }

        return doctorProfile;
    }

    @Override
    public Page<Profile> getDoctorsBySpecialization(DoctorSpecialization specialization, String like, int page, int size) {
        return profileDAO.findDoctorsBySpecialization(specialization, like,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "lastName")));
    }

    @Override
    public DoctorProfile addDoctorSpecializationToDoctor(UUID doctorId, UUID doctorSpecializationId) throws NotFoundException {
        DoctorSpecialization doctorSpecialization = doctorSpecService.getDoctorSpecializationById(doctorSpecializationId);
        DoctorProfile doctorProfile = getDoctorProfileById(doctorId);

        doctorProfile.addDoctorSpecialization(doctorSpecialization);

        return doctorProfileDAO.save(doctorProfile);
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
                if (field.getType() == Date.class) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
                    sdf.setTimeZone(TimeZone.getTimeZone("Europe/Warsaw"));

                    try {
                        v = sdf.parse((String) v);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                ReflectionUtils.setField(field, actProfile, v);
            }
        });

        profileDAO.save(actProfile);
        return actProfile;

    }
}
