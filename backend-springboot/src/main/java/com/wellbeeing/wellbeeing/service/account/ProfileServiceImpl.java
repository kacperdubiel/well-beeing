package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.*;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.repository.account.DoctorProfileDAO;
import com.wellbeeing.wellbeeing.repository.account.ProfileDAO;
import com.wellbeeing.wellbeeing.repository.account.TrainerDAO;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
