package com.wellbeeing.wellbeeing.service.sport;

import com.wellbeeing.wellbeeing.domain.account.TrainerProfile;
import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.domain.sport.*;
import com.wellbeeing.wellbeeing.repository.account.TrainerDAO;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.repository.sport.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("trainingPlanService")
public class TrainingPlanServiceImpl implements TrainingPlanService{
    private TrainingDAO trainingDAO;
    private TrainingPlanDAO trainingPlanDAO;
    private TrainingPositionDAO trainingPositionDAO;
    private UserDAO userDAO;
    private TrainerDAO trainerDAO;
    public TrainingPlanServiceImpl(@Qualifier("trainerDAO") TrainerDAO trainerDAO,
                               @Qualifier("trainingDAO") TrainingDAO trainingDAO,
                               @Qualifier("trainingPlanDAO") TrainingPlanDAO trainingPlanDAO,
                               @Qualifier("trainingPositionDAO") TrainingPositionDAO trainingPositionDAO,
                               @Qualifier("userDAO") UserDAO userDAO) {
//        this.exerciseDAO = exerciseDAO;
        this.trainingDAO = trainingDAO;
//        this.exerciseInTrainingDAO = exerciseInTrainingDAO;
        this.userDAO = userDAO;
        this.trainerDAO = trainerDAO;
        this.trainingPlanDAO = trainingPlanDAO;
        this.trainingPositionDAO = trainingPositionDAO;
    }


    @Override
    public TrainingPlan addTrainingPlan(TrainingPlan trainingPlan, String creatorName, UUID ownerId) {
        User creator = userDAO.findUserByEmail(creatorName).orElse(null); // user null?
        User owner = userDAO.findUserById(ownerId).orElse(null); // user null?
        if ( creator == null || owner == null)
            return null;
        System.out.println("Print id:" + creator.getId());
        trainingPlan.setPlanStatus(EPlanStatus.SCRATCH);
        trainingPlan.setOwner(owner.getProfile());
        trainingPlan.setCreator(creator.getProfile());
        trainingPlanDAO.save(trainingPlan);
        return trainingPlan;
    }

    @Override
    public TrainingPlan addTrainingPlanWithExercises(Training training, String creatorName, List<TrainingPosition> trainingPositions) {
        return null;
    }

    @Override
    public boolean deleteTrainingPlan(long trainingPlanId) {
        if (trainingPlanDAO.findById(trainingPlanId).orElse(null) == null)
            return false;
        trainingPlanDAO.deleteById(trainingPlanId);
        return true;
    }

    @Override
    public TrainingPlan getTrainingPlan(long trainingPlanId) {
        return trainingPlanDAO.findById(trainingPlanId).orElse(null);
    }

    @Override
    public TrainingPosition addPositionToTrainingPlan(long trainingPlanId, long trainingId, Date trainingDate, String clientName) {
        User clientUser = userDAO.findUserByEmail(clientName).orElse(null);
        TrainingPlan updatingPlan = trainingPlanDAO.findById(trainingPlanId).orElse(null);
        Training trainingToBeAdded = trainingDAO.findById(trainingId).orElse(null);
        if (updatingPlan != null && (   clientUser.getId() == updatingPlan.getOwner().getId() ||
                clientUser.getId() == updatingPlan.getCreator().getId()) )
        {
            TrainingPosition newPosition = new TrainingPosition(trainingToBeAdded, updatingPlan, trainingDate);
            trainingPositionDAO.save(newPosition);
            return newPosition;
        }
        return null;
    }

    @Override
    public boolean removePositionFromTrainingPlan(long trainingPlanId, long trainingPositionId, String clientName) {
        User clientUser = userDAO.findUserByEmail(clientName).orElse(null);
        TrainingPlan updatingPlan = trainingPlanDAO.findById(trainingPlanId).orElse(null);
        if (updatingPlan != null && (   clientUser.getId() == updatingPlan.getOwner().getId() ||
                                        clientUser.getId() == updatingPlan.getCreator().getId()) )
        {
            trainingPositionDAO.deleteById(trainingPositionId);
            return true;
        }
        return false;

    }

    @Override
    public List<TrainingPlan> getAllTrainingPlans() {
        return trainingPlanDAO.findAll();
    }

    @Override
    public List<TrainingPosition> getPositionsFromTrainingPlan(long trainingPlanId) {
        TrainingPlan foundTrainingPlan = trainingPlanDAO.findById(trainingPlanId).orElse(null);
        if (foundTrainingPlan == null)
            return null;

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
    public List<TrainingPlan> getMyTrainingPlans(String ownerName) {
        return trainingPlanDAO.findTrainingPlansByOwnerProfileUserEmail(ownerName);
    }

}
