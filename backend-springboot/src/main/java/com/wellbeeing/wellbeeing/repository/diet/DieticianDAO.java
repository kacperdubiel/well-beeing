package com.wellbeeing.wellbeeing.repository.diet;

import com.wellbeeing.wellbeeing.domain.account.DieticianProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("dieticianDAO")
public interface DieticianDAO extends JpaRepository<DieticianProfile, UUID> {
    Optional<DieticianProfile> findDieticianProfileById(UUID dieticianId);
}
