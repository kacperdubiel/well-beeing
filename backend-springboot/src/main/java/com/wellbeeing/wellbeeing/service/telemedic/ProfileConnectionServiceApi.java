package com.wellbeeing.wellbeeing.service.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import com.wellbeeing.wellbeeing.domain.telemedic.ProfileConnection;

import java.util.List;
import java.util.UUID;

public interface ProfileConnectionServiceApi {
    ProfileConnection getProfileConnectionById(UUID profileConnectionId);
    List<ProfileConnection> getProfileConnectionsByProfile(Profile profile);
    List<ProfileConnection> getProfileConnectionsByProfileAndType(Profile profile, EConnectionType connectionType);
    ProfileConnection addProfileConnection(ProfileConnection profileConnection);
    ProfileConnection updateProfileConnection(ProfileConnection updatedProfileConnection);
    boolean deleteProfileConnectionById(UUID profileConnectionId);
}
