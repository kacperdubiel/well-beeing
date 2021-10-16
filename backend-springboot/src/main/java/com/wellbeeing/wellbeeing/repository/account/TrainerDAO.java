package com.wellbeeing.wellbeeing.repository.account;

import com.wellbeeing.wellbeeing.domain.account.TrainerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrainerDAO extends JpaRepository<TrainerProfile, UUID> {
}
