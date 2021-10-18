package com.wellbeeing.wellbeeing.repository.sport;

import com.wellbeeing.wellbeeing.domain.sport.TrainingPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("trainingPositionDAO")
public interface TrainingPositionDAO extends JpaRepository<TrainingPosition, Long> {
}
