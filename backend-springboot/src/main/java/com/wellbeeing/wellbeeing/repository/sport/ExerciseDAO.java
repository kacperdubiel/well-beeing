package com.wellbeeing.wellbeeing.repository.sport;

import com.wellbeeing.wellbeeing.domain.sport.EExerciseType;
import com.wellbeeing.wellbeeing.domain.sport.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("exerciseDAO")
public interface ExerciseDAO extends JpaRepository<Exercise, Long> {
    List<Exercise> findAllByName(String name);
    List<Exercise> findAllByExerciseType(EExerciseType type);

    List<Exercise> findAllByCreator_Id(UUID creator_id);
}
