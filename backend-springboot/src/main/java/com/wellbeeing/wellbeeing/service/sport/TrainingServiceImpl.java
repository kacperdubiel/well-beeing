package com.wellbeeing.wellbeeing.service.sport;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.domain.sport.Exercise;
import com.wellbeeing.wellbeeing.domain.sport.ExerciseInTraining;
import com.wellbeeing.wellbeeing.domain.sport.Training;
import com.wellbeeing.wellbeeing.domain.sport.TrainingPosition;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.repository.sport.ExerciseDAO;
import com.wellbeeing.wellbeeing.repository.sport.ExerciseInTrainingDAO;
import com.wellbeeing.wellbeeing.repository.sport.TrainingDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("trainingService")
public class TrainingServiceImpl implements TrainingService{
    private ExerciseDAO exerciseDAO;
    private TrainingDAO trainingDAO;
    private ExerciseInTrainingDAO exerciseInTrainingDAO;
    private UserDAO userDAO;

    public TrainingServiceImpl(@Qualifier("exerciseDAO") ExerciseDAO exerciseDAO,
                               @Qualifier("trainingDAO") TrainingDAO trainingDAO,
                               @Qualifier("exerciseInTrainingDAO") ExerciseInTrainingDAO exerciseInTrainingDAO,
                               @Qualifier("userDAO") UserDAO userDAO) {
        this.exerciseDAO = exerciseDAO;
        this.trainingDAO = trainingDAO;
        this.exerciseInTrainingDAO = exerciseInTrainingDAO;
        this.userDAO = userDAO;
    }

    @Override
    public Training addTraining(Training training, String creatorName) {
        User user = userDAO.findUserByEmail(creatorName).orElse(null); // user null?
        System.out.println("Print id:" + user.getId());
        training.setCreator(user.getProfile());
        if (trainingDAO.findAllByName(training.getName()).isEmpty()) {
            if (training.getName() != null && training.getTrainingDifficulty() != null)
                trainingDAO.save(training);
            return training;
        }
        return null;
    }

    @Override
    public Training addTrainingWithExercises(Training training, String creatorName, List<ExerciseInTraining> exercises) {
        User user = userDAO.findUserByEmail(creatorName).orElse(null); // user null?
        System.out.println("Print id:" + user.getId());
        training.setCreator(user.getProfile());
        if (trainingDAO.findAllByName(training.getName()).isEmpty()) {
            if (training.getName() != null && training.getTrainingDifficulty() != null)

                exercises.forEach(training::addExerciseToTraining);

                trainingDAO.save(training);
            return training;
        }
        return null;
    }

    @Override
    public boolean deleteTraining(long trainingId) {
        if (trainingDAO.findById(trainingId).orElse(null) != null) {
            trainingDAO.deleteById(trainingId);
            return true;
        }
        return false;
    }

    @Override
    public Training getTraining(long trainingId) {
        return trainingDAO.findById(trainingId).orElse(null);
    }

    @Override
    public ExerciseInTraining addExerciseToTraining(long trainingId, long exerciseId,
                                                    int repetitions, int timeSeconds,
                                                    int series, String clientName ) {
        Training foundTraining = trainingDAO.findById(trainingId).orElse(null);
        Exercise foundExercise = exerciseDAO.findById(exerciseId).orElse(null);
        if ( foundTraining == null || foundExercise == null) {
            return null;
        }
        Profile creator = foundTraining.getCreator();
        if (creator == null || creator.getProfileUser().getUsername().equals(clientName))
        {
            ExerciseInTraining newExerciseInTraining = new ExerciseInTraining(foundTraining, foundExercise, repetitions, timeSeconds, series);
//            foundTraining.addExerciseToTraining(newExerciseInTraining);
//            trainingDAO.save(foundTraining);
//            exerciseDAO.save(foundExercise);
            exerciseInTrainingDAO.save(newExerciseInTraining); // Duplicate Key
            return newExerciseInTraining;
        }
        return null;
    }

    @Override
    public boolean removeExerciseFromTraining(long trainingId, long exerciseId, String clientName) {
        Training foundTraining = trainingDAO.findById(trainingId).orElse(null);
        Exercise foundExercise = exerciseDAO.findById(exerciseId).orElse(null);
        if (foundTraining == null || foundExercise == null)
            return false;
        Profile creator = foundTraining.getCreator();
        if (creator == null || creator.getProfileUser().getUsername().equals(clientName))
        {
            ExerciseInTraining ex_in_training = exerciseInTrainingDAO.getExerciseInTrainingByExerciseAndTraining(foundExercise, foundTraining);
            if (ex_in_training == null)
                return false;
            exerciseInTrainingDAO.delete(ex_in_training);
            return true;
        }
        return false;
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingDAO.findAll();
    }

    @Override
    public List<ExerciseInTraining> getExercisesFromTraining(long trainingId) {
        Training training = trainingDAO.findById(trainingId).orElse(null);
        if (training == null) {
            return null;
        }
        return new ArrayList<>(training.getExerciseInTrainings());
    }

    @Override
    public List<Training> getTrainingByCreatorId(UUID creatorId) {
        return trainingDAO.findAllByCreator_Id(creatorId);
    }

    @Override
    public Training updateTraining(Training training) {
        Training targetTraining = trainingDAO.findById(training.getTraining_id()).orElse(null);
        if(targetTraining == null)
            return null;

        trainingDAO.save(training);
        return training;
    }

    @Override
    public Training partialUpdateTraining(long trainingId, Training training) {
        trainingDAO.save(training);
        return training;
    }

    @Override
    public double getCaloriesBurnedFromUser(long trainingPositionId, String userName) {

        User user = userDAO.findUserByEmail(userName).orElse(null);
        Training training = trainingDAO.findById(trainingPositionId).orElse(null);
        if (user == null || training == null)
            return -1;
        double weight  = user.getProfile().getProfileCard().getWeight();
        System.out.println("User weight: " + weight);
        return training.caloriesBurned(weight);
    }
}
