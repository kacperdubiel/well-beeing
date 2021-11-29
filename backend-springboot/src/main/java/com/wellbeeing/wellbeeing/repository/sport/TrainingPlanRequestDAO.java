package com.wellbeeing.wellbeeing.repository.sport;

import com.wellbeeing.wellbeeing.domain.account.TrainerProfile;
import com.wellbeeing.wellbeeing.domain.sport.TrainingPlanRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("trainingPlanRequestDAO")
public interface TrainingPlanRequestDAO extends JpaRepository<TrainingPlanRequest, Long>, JpaSpecificationExecutor<TrainingPlanRequest> {
    List<TrainingPlanRequest> findTrainingPlanRequestsBySubmitterId(UUID id);

    List<TrainingPlanRequest> findTrainingPlanRequestsByTrainer(TrainerProfile profile);

}
