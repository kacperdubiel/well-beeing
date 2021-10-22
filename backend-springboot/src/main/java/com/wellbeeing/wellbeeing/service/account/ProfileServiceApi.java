package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.Profile;

import java.util.UUID;

public interface ProfileServiceApi {
    Profile getProfileById(UUID senderId);
}
