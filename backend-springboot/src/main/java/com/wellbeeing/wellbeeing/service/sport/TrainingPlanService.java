package com.wellbeeing.wellbeeing.service.sport;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.IllegalArgumentException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.sport.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TrainingPlanService {
    TrainingPlan addTrainingPlan(TrainingPlan trainingPlan, String creatorName, UUID ownerId, long requestId);

    TrainingPlan addTrainingPlanWithExercises(Training training, String creatorName, List<TrainingPosition> trainingPositions);

    boolean deleteTrainingPlan(long trainingPlanId) throws NotFoundException;

    TrainingPlan getTrainingPlan(long trainingPlanId, String userName) throws NotFoundException;

    TrainingPosition addPositionToTrainingPlan(long trainingPlanId, long trainingId, Date trainingDate, String timeOfDay, String clientName) throws NotFoundException, IllegalArgumentException;

    //    TrainingPosition addManyPositionsToTrainingPlan(long trainingPlanId, List<Long> trainingIds, List<Date> trainingDates, String clientName);
    boolean removePositionFromTrainingPlan(long trainingPlanId, long trainingPositionId, String clientName) throws NotFoundException;

    List<TrainingPlan> getAllTrainingPlans();

    List<TrainingPosition> getPositionsFromTrainingPlan(long trainingPlanId) throws NotFoundException;

    List<TrainingPlan> getTrainingPlansByCreatorId(UUID creatorId);

    List<TrainingPlan> getTrainingPlansByOwnerId(UUID ownerId);

    List<TrainingPlan> getMyTrainingPlans(String ownerName) throws NotFoundException;

    List<TrainingPlanRequest> getMyRequests(String userName) throws NotFoundException;

    List<TrainingPlanRequest> getTrainersRequests(String trainerName) throws NotFoundException;

    Page<TrainingPlanRequest> getMyRequestsFiltered(Specification<TrainingPlanRequest> spec, Pageable page, String userName) throws NotFoundException;

    TrainingPlanRequest changeTrainingPlanRequestStatus(String userName, long requestId, String newStatus) throws NotFoundException;

    TrainingPlanRequest getTrainingPlanRequest(long requestId, String userName) throws NotFoundException, ForbiddenException;

    TrainingPlanRequest sendRequestToTrainer(UUID trainerId, String submitterName, String message, LocalDateTime beginningDate) throws NotFoundException;

    TrainingPosition updateTrainingPositionStatus(Long positionId, String newStatus, String userName) throws IllegalArgumentException, NotFoundException;

    TrainingPlan partialUpdateTrainingPlan(TrainingPlan trainingPlan);

    long generateTrainingPlanForMe(List<Integer> trainingsPerDay, long activityGoalId, Profile profile, EWorkoutStrategy strategy, Date beginningDate, String generatorStrategy) throws NotFoundException, IllegalArgumentException;
}
