package com.wellbeeing.wellbeeing.repository.account;

import com.wellbeeing.wellbeeing.domain.account.DieticianProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository("dieticianProfileDAO")
public interface DieticianProfileDAO extends JpaRepository<DieticianProfile, UUID> {
    Optional<DieticianProfile> findDieticianProfileById(UUID dieticianId);
}
