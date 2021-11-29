package com.wellbeeing.wellbeeing.service.sport;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.account.TrainerProfile;
import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.domain.exception.IllegalArgumentException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.sport.*;
import com.wellbeeing.wellbeeing.repository.account.TrainerProfileDAO;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.repository.sport.*;
import com.wellbeeing.wellbeeing.service.sport.alg.TrainingPlanGeneratorStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service("trainingPlanService")
public class TrainingPlanServiceImpl implements TrainingPlanService {
    private final TrainingDAO trainingDAO;
    private final TrainingPlanDAO trainingPlanDAO;
    private final TrainingPositionDAO trainingPositionDAO;
    private final UserDAO userDAO;
    private final TrainerProfileDAO trainerProfileDAO;
    private final TrainingPlanRequestDAO trainingPlanRequestDAO;
    private final ActivityGoalDAO activityGoalDAO;
    private final TrainingPlanGeneratorStrategy planGeneratorStrategy;

    public TrainingPlanServiceImpl(@Qualifier("trainerProfileDAO") TrainerProfileDAO trainerProfileDAO,
                                   @Qualifier("trainingDAO") TrainingDAO trainingDAO,
                                   @Qualifier("trainingPlanDAO") TrainingPlanDAO trainingPlanDAO,
                                   @Qualifier("trainingPositionDAO") TrainingPositionDAO trainingPositionDAO,
                                   @Qualifier("trainingPlanRequestDAO") TrainingPlanRequestDAO trainingPlanRequestDAO,
                                   @Qualifier("userDAO") UserDAO userDAO,
                                   @Qualifier("activityGoalDAO") ActivityGoalDAO activityGoalDAO,
                                   TrainingPlanGeneratorStrategy planGeneratorStrategy) {
//        this.exerciseDAO = exerciseDAO;
        this.trainingDAO = trainingDAO;
//        this.exerciseInTrainingDAO = exerciseInTrainingDAO;
        this.userDAO = userDAO;
        this.trainerProfileDAO = trainerProfileDAO;
        this.trainingPlanDAO = trainingPlanDAO;
        this.trainingPositionDAO = trainingPositionDAO;
        this.trainingPlanRequestDAO = trainingPlanRequestDAO;
        this.activityGoalDAO = activityGoalDAO;
        this.planGeneratorStrategy = planGeneratorStrategy;
    }


    @Override
    public TrainingPlan addTrainingPlan(TrainingPlan trainingPlan, String creatorName, UUID ownerId, long requestId) {
        User creator = userDAO.findUserByEmail(creatorName).orElse(null); // user null?
        User owner = userDAO.findUserById(ownerId).orElse(null); // user null?
        if (creator == null)
            return null;
        if (owner == null)
            owner = creator;
        TrainingPlanRequest request = trainingPlanRequestDAO.findById(requestId).orElse(null);

        System.out.println("Print id:" + creator.getId());
        trainingPlan.setPlanStatus(EPlanStatus.SCRATCH);
        trainingPlan.setOwner(owner.getProfile());
        trainingPlan.setCreator(creator.getProfile());
        if (request != null)
            trainingPlan.setRequest(request);
        trainingPlanDAO.save(trainingPlan);
        return trainingPlan;
    }

    @Override
    public TrainingPlan addTrainingPlanWithExercises(Training training, String creatorName, List<TrainingPosition> trainingPositions) {
        return null;
    }

    @Override
    public boolean deleteTrainingPlan(long trainingPlanId) throws NotFoundException {
        if (trainingPlanDAO.findById(trainingPlanId).orElse(null) == null)
            throw new NotFoundException(String.format("Training plan with id=%d doesn't exist", trainingPlanId));
        return true;
    }

    @Override
    public TrainingPlan getTrainingPlan(long trainingPlanId, String userName) throws NotFoundException {
        User clientUser = userDAO.findUserByEmail(userName).orElse(null);
        double weight = 0;
        try {
            weight = clientUser.getProfile().getProfileCard().getWeight();
        } catch (NullPointerException e) {
            System.out.println("User has no profile or profile card!");
        }
        TrainingPlan plan = trainingPlanDAO.findById(trainingPlanId).orElse(null);
        if (plan == null) {
            throw new NotFoundException(String.format("Training plan with id=%d doesn't exist", trainingPlanId));
        }
        double finalWeight = weight;
        plan.getTrainingPositions().forEach(
                pos -> {
                    pos.getTraining().setCaloriesBurned(pos.getTraining().caloriesBurned(finalWeight));
                    pos.getTraining().getExerciseInTrainings().forEach(ex -> ex.setCaloriesBurned(ex.countCaloriesPerExerciseDuration(finalWeight)));
                }
        );
//        System.out.println("Przed planem");
//        PDFFromTrainingPlan.generatePDFFromTrainingPlan(plan, "Some plan");
//        System.out.println("Po planie");
        return plan;
    }

    @Override
    public TrainingPosition addPositionToTrainingPlan(long trainingPlanId, long trainingId, Date trainingDate, String timeOfDay, String clientName) throws NotFoundException, IllegalArgumentException {
        User clientUser = userDAO.findUserByEmail(clientName).orElse(null);
        TrainingPlan updatingPlan = trainingPlanDAO.findById(trainingPlanId).orElse(null);
        Training trainingToBeAdded = trainingDAO.findById(trainingId).orElse(null);
        if (updatingPlan == null) {
            throw new NotFoundException(String.format("Training plan with id=%d doesn't exist", trainingPlanId));
        }
        if (clientUser == null) {
            throw new NotFoundException(String.format("Couldn't find user with name=%s", clientName));
        }
        if ((clientUser.getId() != updatingPlan.getOwner().getId() &&
                clientUser.getId() != updatingPlan.getCreator().getId())) {
            throw new NotFoundException("You can't edit that training plan!");
        }
        ETimeOfDay eTimeOfDay;
        try {
            eTimeOfDay = ETimeOfDay.valueOf(timeOfDay);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid timeOfDay value!");
        }
//        (trainingToBeAdded, updatingPlan, trainingDate)
        TrainingPosition newPosition = TrainingPosition.builder()
                .training(trainingToBeAdded)
                .trainingPlan(updatingPlan)
                .trainingDate(trainingDate)
                .timeOfDay(eTimeOfDay).build();
        newPosition = trainingPositionDAO.saveAndFlush(newPosition);
//        updatingPlan.addTrainingPosition(newPosition);
//        trainingPlanDAO.saveAndFlush(updatingPlan);
        return newPosition;
    }

    @Override
    public boolean removePositionFromTrainingPlan(long trainingPlanId, long trainingPositionId, String clientName) throws NotFoundException {
        User clientUser = userDAO.findUserByEmail(clientName).orElse(null);
        TrainingPlan updatingPlan = trainingPlanDAO.findById(trainingPlanId).orElse(null);
        if (updatingPlan == null) {
            throw new NotFoundException(String.format("Training plan with id=%d doesn't exist", trainingPlanId));
        }
        if (clientUser == null) {
            throw new NotFoundException(String.format("Couldn't find user with name=%s", clientName));
        }
        if ((clientUser.getId() != updatingPlan.getOwner().getId() &&
                clientUser.getId() != updatingPlan.getCreator().getId())) {
            throw new NotFoundException("You can't edit that training plan!");
        }
        trainingPositionDAO.deleteById(trainingPositionId);
        return true;

    }

    @Override
    public List<TrainingPlan> getAllTrainingPlans() {
        return trainingPlanDAO.findAll();
    }

    @Override
    public List<TrainingPosition> getPositionsFromTrainingPlan(long trainingPlanId) throws NotFoundException {
        TrainingPlan foundTrainingPlan = trainingPlanDAO.findById(trainingPlanId).orElse(null);
        if (foundTrainingPlan == null)
            throw new NotFoundException(String.format("Training plan with id=%d doesn't exist", trainingPlanId));

        return new ArrayList<>(foundTrainingPlan.getTrainingPositions());
    }

    @Override
    public List<TrainingPlan> getTrainingPlansByCreatorId(UUID creatorId) {
        return trainingPlanDAO.findTrainingPlansByCreatorId(creatorId);
    }

    @Override
    public List<TrainingPlan> getTrainingPlansByOwnerId(UUID ownerId) {
        return trainingPlanDAO.findTrainingPlansByOwnerId(ownerId);
    }

    @Override
    public List<TrainingPlan> getMyTrainingPlans(String ownerName) throws NotFoundException {
        User owner = userDAO.findUserByEmail(ownerName).orElse(null);
        if (owner == null)
            throw new NotFoundException(String.format("User with name=%s doesn't exist", ownerName));
        double weight = 0;
        try {
            weight = owner.getProfile().getProfileCard().getWeight();
        } catch (NullPointerException e) {
            System.out.println("User has no profile or profile card!");
        }
        List<TrainingPlan> myPlans = trainingPlanDAO.findTrainingPlansByOwnerProfileUserEmail(ownerName);
        double finalWeight = weight;
        myPlans.forEach(plan -> {
            plan.getTrainingPositions().forEach(
                    pos -> {
                        pos.getTraining().setCaloriesBurned(pos.getTraining().caloriesBurned(finalWeight));
                        pos.getTraining().getExerciseInTrainings().forEach(ex -> ex.setCaloriesBurned(ex.countCaloriesPerExerciseDuration(finalWeight)));
                    }
            );
            plan.setCaloriesBurned(plan.getTrainingPositions().stream().map(pos -> pos.getTraining().getCaloriesBurned()).mapToInt(num -> num).sum());
        });
        return trainingPlanDAO.findTrainingPlansByOwnerProfileUserEmail(ownerName).stream().sorted(Comparator.comparing(TrainingPlan::getBeginningDate)).collect(Collectors.toList());
    }

    @Override
    public List<TrainingPlanRequest> getMyRequests(String userName) throws NotFoundException {
        User submitterUser = userDAO.findUserByEmail(userName).orElse(null);
        if (submitterUser == null)
            throw new NotFoundException(String.format("User with name=%s doesn't exist", userName));
        return trainingPlanRequestDAO.findTrainingPlanRequestsBySubmitterId(submitterUser.getId());
    }

    @Override
    public List<TrainingPlanRequest> getTrainersRequests(String trainerName) throws NotFoundException {
        User trainerUser = userDAO.findUserByEmail(trainerName).orElse(null);
        if (trainerUser == null)
            throw new NotFoundException(String.format("User with name=%s doesn't exist", trainerName));
        TrainerProfile trainerProfile = trainerProfileDAO.findById(trainerUser.getId()).orElse(null);
        if (trainerProfile == null)
            throw new NotFoundException(String.format("Trainer with name='%s' doesn't exist", trainerName));

        return trainingPlanRequestDAO.findTrainingPlanRequestsByTrainer(trainerProfile);
    }

    @Override
    public Page<TrainingPlanRequest> getMyRequestsFiltered(Specification<TrainingPlanRequest> spec, Pageable pageable, String userName) throws NotFoundException {
        User submitterUser = userDAO.findUserByEmail(userName).orElse(null);
        if (submitterUser == null)
            throw new NotFoundException(String.format("User with name=%s doesn't exist", userName));
        return trainingPlanRequestDAO.findAll(spec, pageable);
    }

    @Override
    public TrainingPlanRequest changeTrainingPlanRequestStatus(String userName, long requestId, String newStatus) throws NotFoundException {
        User user = userDAO.findUserByEmail(userName).orElse(null);
        if (user == null)
            throw new NotFoundException(String.format("User with name=%s doesn't exist", userName));
        TrainingPlanRequest editedRequest = trainingPlanRequestDAO.findById(requestId).orElse(null);
        if (editedRequest == null)
            throw new NotFoundException(String.format("Request with id=%d doesn't exist", requestId));

        if (editedRequest.getTrainer() == null)
            throw new NotFoundException("Request has no trainer assigned!");
        if (editedRequest.getTrainer().getId().equals(user.getId()) || editedRequest.getSubmitter().getId().equals(user.getId())) {
            if (editedRequest.getSubmitter().getId().equals(user.getId()) && !newStatus.equals(ERequestStatus.CANCELLED.toString()))
                throw new NotFoundException("As a submitter, you can change status only to 'CANCELLED'!");
            editedRequest.setRequestStatus(ERequestStatus.valueOf(newStatus));
            trainingPlanRequestDAO.save(editedRequest);
            return editedRequest;
        } else
            throw new NotFoundException("You're not connected to this request! You can't edit it!");
    }

    @Override
    public TrainingPlanRequest sendRequestToTrainer(UUID trainerId, String submitterName, String message, LocalDateTime beginningDate) throws NotFoundException {
        TrainerProfile trainerProfile = trainerProfileDAO.findById(trainerId).orElse(null);
        User submitterUser = userDAO.findUserByEmail(submitterName).orElse(null);
        if (submitterUser == null)
            throw new NotFoundException(String.format("User with name=%s doesn't exist", submitterName));

        if (trainerProfile == null)
            throw new NotFoundException(String.format("Trainer with id='%s' doesn't exists", trainerId));
        System.out.println("Data rozpoczęcia:" + beginningDate.plusHours(1));
        TrainingPlanRequest newRequest = TrainingPlanRequest.builder()
                .submitter(submitterUser.getProfile())
                .trainer(trainerProfile)
                .message(message)
                .beginningDate(beginningDate.plusHours(1))
                .requestStatus(ERequestStatus.SUBMITTED)
                .build();
        trainingPlanRequestDAO.save(newRequest);
        return newRequest;

    }

    @Override
    public TrainingPosition updateTrainingPositionStatus(Long positionId, String newStatus, String userName) throws IllegalArgumentException, NotFoundException {
        TrainingPosition foundPosition = trainingPositionDAO.findById(positionId).orElse(null);
        if (foundPosition == null)
            throw new NotFoundException(String.format("Position with id=%s doesn't exist", positionId));
        ETrainingStatus status;
        try {
            status = ETrainingStatus.valueOf(newStatus.toUpperCase());
        } catch (java.lang.IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        foundPosition.setTrainingStatus(status);
        trainingPositionDAO.save(foundPosition);
        return foundPosition;
    }

    @Override
    public TrainingPlan partialUpdateTrainingPlan(TrainingPlan trainingPlan) {
        trainingPlanDAO.save(trainingPlan);
        return trainingPlan;
    }

    @Override
    public long generateTrainingPlanForMe(List<Integer> trainingsPerDay, long activityGoalId, Profile profile, EWorkoutStrategy strategy, Date beginningDate) throws NotFoundException, IllegalArgumentException {
        ActivityGoal goal = activityGoalDAO.findById(activityGoalId).orElse(null);
        if (goal == null)
            return -1;
        return planGeneratorStrategy.generateTrainingPlan(trainingsPerDay, goal, profile, strategy, beginningDate);
    }

}
