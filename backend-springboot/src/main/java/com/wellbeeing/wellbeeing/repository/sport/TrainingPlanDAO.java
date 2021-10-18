package com.wellbeeing.wellbeeing.repository.sport;

import com.wellbeeing.wellbeeing.domain.account.TrainerProfile;
import com.wellbeeing.wellbeeing.domain.sport.TrainingPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("trainingPlanDAO")
public interface TrainingPlanDAO extends JpaRepository<TrainingPlan, Long> {
    List<TrainingPlan> findTrainingPlansByOwnerId(UUID ownerId);
    List<TrainingPlan> findTrainingPlansByOwnerProfileUserEmail(String userName);
    List<TrainingPlan> findTrainingPlansByCreatorId(UUID creatorId);
}
