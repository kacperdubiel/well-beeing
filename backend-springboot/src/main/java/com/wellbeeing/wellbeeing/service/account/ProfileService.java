package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface ProfileService {
    Profile getProfileById(UUID profileId);
}
