package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.DoctorProfile;
import com.wellbeeing.wellbeeing.domain.account.DoctorSpecialization;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.account.TrainerProfile;
import com.wellbeeing.wellbeeing.domain.social.RoleRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ProfileService {
    Profile getProfileById(UUID profileId) throws NotFoundException;
    Page<Profile> getProfilesFiltered(Specification<Profile> profileSpec, Pageable pageable);
    Profile partialUpdateProfile(UUID profileId, Map<String, Object> fields) throws NotFoundException;

    DoctorProfile getDoctorProfileById(UUID doctorProfileId) throws NotFoundException;
    Page<Profile> getDoctorsBySpecialization(DoctorSpecialization specialization, String like, int page, int size);
    DoctorProfile addDoctorSpecializationToDoctor(UUID doctorId, UUID doctorSpecializationId) throws NotFoundException;

    List<TrainerProfile> getTrainersProfiles();
    TrainerProfile getTrainerProfileById(UUID doctorProfileId) throws NotFoundException;
    Page<Profile> getTrainersProfiles(Specification<Profile> spec, Pageable pageable);

    Page<Profile> getDieticiansProfiles(String like, int page, int size);


}
