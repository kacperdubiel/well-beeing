package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("profileService")
public class ProfileService implements ProfileServiceApi {
    @Override
    public Profile getProfileById(UUID senderId) {
        return null;
    }
}
