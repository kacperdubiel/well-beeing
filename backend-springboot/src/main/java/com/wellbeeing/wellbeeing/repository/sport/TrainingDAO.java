package com.wellbeeing.wellbeeing.repository.sport;

import com.wellbeeing.wellbeeing.domain.sport.Training;
import com.wellbeeing.wellbeeing.repository.account.TrainerDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("trainingDAO")
public interface TrainingDAO extends JpaRepository<Training, Long> {
    List<TrainingDAO> findAllByCreator_Id(UUID id);
}
