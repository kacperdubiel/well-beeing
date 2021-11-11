package com.wellbeeing.wellbeeing.repository.sport;

import com.wellbeeing.wellbeeing.domain.sport.ReportExercise;
import com.wellbeeing.wellbeeing.domain.sport.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReportExerciseDAO extends JpaRepository<ReportExercise, Long>, JpaSpecificationExecutor<ReportExercise> {
}
