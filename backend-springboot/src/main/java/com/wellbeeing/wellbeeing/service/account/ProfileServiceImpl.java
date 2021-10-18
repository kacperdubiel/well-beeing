package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService {
    @Override
    public Profile getProfileById(UUID profileId) {
        return null;
    }
}
