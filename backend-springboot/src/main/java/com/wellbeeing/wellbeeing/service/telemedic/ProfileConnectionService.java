package com.wellbeeing.wellbeeing.service.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import com.wellbeeing.wellbeeing.domain.telemedic.ProfileConnection;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ProfileConnectionService {
    ProfileConnection getProfileConnectionById(UUID profileConnectionId) throws NotFoundException;
    Page<ProfileConnection> getProfileConnectionsByProfileAndTypeAndIsAccepted(
            Profile profile, EConnectionType connectionType, boolean isAccepted, int page, int size);
    Page<ProfileConnection> getProfileConnectionsByConnectedWithAndTypeAndIsAccepted(
            Profile connectedWith, EConnectionType connectionType, boolean isAccepted, int page, int size);
    ProfileConnection getProfileConnectionByProfileAndConnectedWithAndType(
            Profile profile, Profile connectedWith, EConnectionType connectionType);
    ProfileConnection getProfileConnectionByProfileAndConnectedWithAndTypeAndIsAccepted(
            Profile profile, Profile connectedWith, EConnectionType connectionType);
    ProfileConnection addProfileConnection(ProfileConnection profileConnection) throws ConflictException, NotFoundException;
    ProfileConnection markAsAccepted(ProfileConnection updatedProfileConnection) throws NotFoundException, ConflictException;
    void deleteProfileConnectionById(UUID profileConnectionId) throws NotFoundException;
}
