package com.wellbeeing.wellbeeing.repository.account;

import com.wellbeeing.wellbeeing.domain.account.TrainerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository("trainerDAO")
public interface TrainerDAO extends JpaRepository<TrainerProfile, UUID> {
}
