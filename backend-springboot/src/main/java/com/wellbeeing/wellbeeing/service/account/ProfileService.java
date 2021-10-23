package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.Profile;

import java.util.UUID;

public interface ProfileService {
    Profile getProfileById(UUID profileId);
    Profile updateProfile(Profile profile, UUID profileId);
}
