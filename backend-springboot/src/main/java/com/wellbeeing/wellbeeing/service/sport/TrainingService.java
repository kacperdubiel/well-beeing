package com.wellbeeing.wellbeeing.service.sport;

import com.wellbeeing.wellbeeing.domain.sport.Exercise;
import com.wellbeeing.wellbeeing.domain.sport.ExerciseInTraining;
import com.wellbeeing.wellbeeing.domain.sport.Training;
import com.wellbeeing.wellbeeing.domain.sport.TrainingPosition;
import javassist.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface TrainingService {
    Training getTraining(long trainingId);
    List<Training> getAllTrainings();
    List<ExerciseInTraining> getExercisesFromTraining(long trainingId) throws NotFoundException;
    Training addTraining(Training training, String creatorName) throws NotFoundException;
    Training addTrainingWithExercises(Training training, String creatorName, List<ExerciseInTraining> exercisesInTraining) throws NotFoundException;
    boolean deleteTraining(long trainingId) throws NotFoundException;
    ExerciseInTraining addExerciseToTraining(long trainingId, long exerciseId, int repetitions, int timeSeconds, int series, String clientName) throws NotFoundException;
    boolean removeExerciseFromTraining(long trainingId, long exerciseId, String clientName) throws NotFoundException;
    List<Training> getTrainingByCreatorId(UUID creatorId);
    Training updateTraining(Training training) throws NotFoundException;

    double getCaloriesBurnedFromUser(long trainingPositionId, String userName) throws NotFoundException;
}
