package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.Profile;

import com.wellbeeing.wellbeeing.domain.account.TrainerProfile;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ProfileService {
    Profile getProfileById(UUID profileId) throws NotFoundException;
    List<TrainerProfile> getTrainersProfiles();
    Profile partialUpdateProfile(UUID profileId, Map<String, Object> fields) throws NotFoundException;
}
