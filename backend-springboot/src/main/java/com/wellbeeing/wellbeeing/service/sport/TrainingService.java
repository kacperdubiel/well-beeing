package com.wellbeeing.wellbeeing.service.sport;

import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.sport.ExerciseInTraining;
import com.wellbeeing.wellbeeing.domain.sport.Training;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public interface TrainingService {
    Training getTraining(long trainingId, String userName) throws NotFoundException;

    List<Training> getAllTrainings();

    List<Training> getAllTrainings(String userName);

    Page<Training> getAllTrainings(Pageable pageable, String userName);

    Page<Training> getAllTrainingsFiltered(Specification<Training> trainingSpec,
                                           Pageable pageable, String userName) throws NotFoundException;

    List<ExerciseInTraining> getExercisesFromTraining(long trainingId) throws NotFoundException;

    Training addTraining(Training training, String creatorName) throws NotFoundException;

    Training addTrainingWithExercises(Training training, String creatorName, List<ExerciseInTraining> exercisesInTraining) throws NotFoundException;

    boolean deleteTraining(long trainingId) throws NotFoundException;

    ExerciseInTraining addExerciseToTraining(long trainingId, long exerciseId, int repetitions, int timeSeconds, int series, String clientName) throws NotFoundException, ForbiddenException, ConflictException;

    boolean removeExerciseFromTraining(long trainingId, long exerciseId, String clientName) throws NotFoundException;

    List<Training> getTrainingByCreatorId(UUID creatorId);

    Training updateTraining(Training training) throws NotFoundException;

    Training partialUpdateTraining(Training training);

    double getCaloriesBurnedFromUser(long trainingPositionId, String userName) throws NotFoundException;
}
