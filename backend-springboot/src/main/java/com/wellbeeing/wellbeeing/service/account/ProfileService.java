package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.account.TrainerProfile;
import com.wellbeeing.wellbeeing.domain.sport.TrainingPlan;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface ProfileService {
    Profile getProfileById(UUID profileId);
    List<TrainerProfile> getTrainersProfiles();

    Profile updateProfile(Profile profile, UUID profileId) throws NotFoundException;
}
