package com.wellbeeing.wellbeeing.service.sport;

import com.wellbeeing.wellbeeing.domain.sport.*;
import javassist.NotFoundException;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TrainingPlanService {
    TrainingPlan addTrainingPlan(TrainingPlan trainingPlan, String creatorName, UUID ownerId);
    TrainingPlan addTrainingPlanWithExercises(Training training, String creatorName, List<TrainingPosition> trainingPositions);
    boolean deleteTrainingPlan(long trainingPlanId) throws NotFoundException;
    TrainingPlan getTrainingPlan(long trainingPlanId);
    TrainingPosition addPositionToTrainingPlan(long trainingPlanId, long trainingId, Date trainingDate, String clientName) throws NotFoundException;
//    TrainingPosition addManyPositionsToTrainingPlan(long trainingPlanId, List<Long> trainingIds, List<Date> trainingDates, String clientName);
    boolean removePositionFromTrainingPlan(long trainingPlanId, long trainingPositionId, String clientName) throws NotFoundException;
    List<TrainingPlan> getAllTrainingPlans();
    List<TrainingPosition> getPositionsFromTrainingPlan(long trainingPlanId) throws NotFoundException;
    List<TrainingPlan> getTrainingPlansByCreatorId(UUID creatorId);
    List<TrainingPlan> getTrainingPlansByOwnerId(UUID ownerId);
    List<TrainingPlan> getMyTrainingPlans(String ownerName);
    List<TrainingPlanRequest> getMyRequests(String userName) throws NotFoundException;
    List<TrainingPlanRequest> getTrainersRequests(String trainerName) throws NotFoundException;
    TrainingPlanRequest changeTrainingPlanRequestStatus(String userName, long requestId, String newStatus) throws NotFoundException;
    TrainingPlanRequest sendRequestToTrainer(UUID trainerId, String submitterName, String message) throws NotFoundException;
}
