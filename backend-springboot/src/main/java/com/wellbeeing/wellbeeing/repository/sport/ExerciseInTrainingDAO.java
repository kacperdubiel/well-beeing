package com.wellbeeing.wellbeeing.repository.sport;

import com.wellbeeing.wellbeeing.domain.sport.Exercise;
import com.wellbeeing.wellbeeing.domain.sport.ExerciseInTraining;
import com.wellbeeing.wellbeeing.domain.sport.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseInTrainingDAO extends JpaRepository<ExerciseInTraining, Long> {
    ExerciseInTraining getExerciseInTrainingByExerciseAndTraining(Exercise exercise, Training training);
    ExerciseInTraining getExerciseInTrainingByExercise_ExerciseIdAndTraining_TrainingId(long exercise_exerciseId, long training_trainingId);
}
