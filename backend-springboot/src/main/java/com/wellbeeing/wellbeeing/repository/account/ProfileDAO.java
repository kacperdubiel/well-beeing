package com.wellbeeing.wellbeeing.repository.account;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("profileDAO")
public interface ProfileDAO extends JpaRepository<Profile, UUID> {
}
