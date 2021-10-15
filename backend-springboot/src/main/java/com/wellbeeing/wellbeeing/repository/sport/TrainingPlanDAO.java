package com.wellbeeing.wellbeeing.repository.sport;

import com.wellbeeing.wellbeeing.domain.sport.TrainingPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("trainingPlanDAO")
public interface TrainingPlanDAO extends JpaRepository<TrainingPlan, Long> {
}
