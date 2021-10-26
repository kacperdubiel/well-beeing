package com.wellbeeing.wellbeeing.repository.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import com.wellbeeing.wellbeeing.domain.telemedic.ProfileConnection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("profileConnectionDAO")
public interface ProfileConnectionDAO extends JpaRepository<ProfileConnection, UUID> {
    Page<ProfileConnection> findByProfileAndConnectionTypeAndIsAccepted(
            Profile profile, EConnectionType connectionType, boolean isAccepted, Pageable pageable);
    Page<ProfileConnection> findByConnectedWithAndConnectionTypeAndIsAccepted(
            Profile connectedWith, EConnectionType connectionType, boolean isAccepted, Pageable pageable);
    ProfileConnection findByProfileAndConnectedWithAndConnectionType(Profile profile, Profile connectedWith, EConnectionType connectionType);
}
