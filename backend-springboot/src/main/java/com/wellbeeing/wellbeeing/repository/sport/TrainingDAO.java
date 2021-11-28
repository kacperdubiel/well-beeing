package com.wellbeeing.wellbeeing.repository.sport;

import com.wellbeeing.wellbeeing.domain.sport.ETrainingDifficulty;
import com.wellbeeing.wellbeeing.domain.sport.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("trainingDAO")
public interface TrainingDAO extends JpaRepository<Training, Long>, JpaSpecificationExecutor<Training> {
    List<Training> findAllByCreator_Id(UUID id);
    List<Training> findAllByName(String name);
    List<Training> findAllByTrainingDifficulty(ETrainingDifficulty type);
}
