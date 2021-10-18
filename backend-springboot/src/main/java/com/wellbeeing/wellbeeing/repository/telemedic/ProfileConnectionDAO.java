package com.wellbeeing.wellbeeing.repository.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import com.wellbeeing.wellbeeing.domain.telemedic.ProfileConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("profileConnectionDAO")
public interface ProfileConnectionDAO extends JpaRepository<ProfileConnection, UUID> {
    List<ProfileConnection> findByProfile(Profile profile);
    List<ProfileConnection> findByProfileAndConnectionType(Profile profile, EConnectionType connectionType);
}
