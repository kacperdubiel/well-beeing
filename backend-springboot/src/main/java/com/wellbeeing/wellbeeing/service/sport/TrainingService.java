package com.wellbeeing.wellbeeing.service.sport;

import com.wellbeeing.wellbeeing.domain.sport.Exercise;
import com.wellbeeing.wellbeeing.domain.sport.ExerciseInTraining;
import com.wellbeeing.wellbeeing.domain.sport.Training;

import java.util.List;
import java.util.UUID;

public interface TrainingService {
    Training addTraining(Training training, String creatorName);
    Training addTrainingWithExercises(Training training, String creatorName, List<ExerciseInTraining> exercisesInTraining);
    boolean deleteTraining(long trainingId);
    Training getTraining(long trainingId);
    ExerciseInTraining addExerciseToTraining(long trainingId, long exerciseId, int repetitions, int timeSeconds, int series, String clientName);
    boolean removeExerciseFromTraining(long trainingId, long exerciseId, String clientName);
    List<Training> getAllTrainings();
    List<ExerciseInTraining> getExercisesFromTraining(long trainingId);
    List<Training> getTrainingByCreatorId(UUID creatorId);
    Training updateTraining(Training training);
    Training partialUpdateTraining(long trainingId, Training training);
}
