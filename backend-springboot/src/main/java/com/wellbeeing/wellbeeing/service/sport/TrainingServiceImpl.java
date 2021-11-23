package com.wellbeeing.wellbeeing.service.sport;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.sport.Exercise;
import com.wellbeeing.wellbeeing.domain.sport.ExerciseInTraining;
import com.wellbeeing.wellbeeing.domain.sport.Training;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.repository.sport.ExerciseDAO;
import com.wellbeeing.wellbeeing.repository.sport.ExerciseInTrainingDAO;
import com.wellbeeing.wellbeeing.repository.sport.TrainingDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("trainingService")
public class TrainingServiceImpl implements TrainingService {
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
    public Training addTraining(Training training, String creatorName) throws NotFoundException {
        User user = userDAO.findUserByEmail(creatorName).orElse(null); // user null?
        training.setCreator(user != null ? user.getProfile() : null);
        if (trainingDAO.findAllByName(training.getName()).isEmpty()) {
            if (training.getName() == null || training.getName().equals("")) {
                throw new NotFoundException("Training must have a name!");
            } else if (training.getTrainingDifficulty() == null) {
                throw new NotFoundException("Training must have a difficulty!");
            }
            trainingDAO.save(training);
            return training;
        }
        throw new NotFoundException(String.format("Training with name=%s already exists", training.getName()));
    }

    @Override //TODO
    public Training addTrainingWithExercises(Training training, String creatorName, List<ExerciseInTraining> exercises) throws NotFoundException {
        User user = userDAO.findUserByEmail(creatorName).orElse(null); // user null?
        training.setCreator(user.getProfile());
        if (trainingDAO.findAllByName(training.getName()).isEmpty()) {
            if (training.getName() != null && training.getTrainingDifficulty() != null) {
                exercises.forEach(training::addExerciseToTraining);
                trainingDAO.save(training);
            }
            return training;
        }
        throw new NotFoundException(String.format("Training with name=%s already exists", training.getName()));
    }

    @Override
    public boolean deleteTraining(long trainingId) throws NotFoundException {
        if (trainingDAO.findById(trainingId).orElse(null) != null) {
            trainingDAO.deleteById(trainingId);
            return true;
        }
        throw new NotFoundException(String.format("Training with id=%d doesn't exist", trainingId));
    }

    @Override
    public Training getTraining(long trainingId, String userName) throws NotFoundException {
        User clientUser = userDAO.findUserByEmail(userName).orElse(null);
        double weight = 0;
        try {
            weight = clientUser.getProfile().getProfileCard().getWeight();
        } catch (NullPointerException e) {
            System.out.println("User has no profile or profile card!");
        }
        double finalWeight = weight;
        Training training = trainingDAO.findById(trainingId).orElse(null);
        if (training == null) {
            throw new NotFoundException(String.format("Training plan with id=%d doesn't exist", trainingId));
        }
        training.setCaloriesBurned(training.caloriesBurned(finalWeight));
        return training;
    }

    @Override
    public ExerciseInTraining addExerciseToTraining(long trainingId, long exerciseId,
                                                    int repetitions, int timeSeconds,
                                                    int series, String clientName) throws NotFoundException {
        Training foundTraining = trainingDAO.findById(trainingId).orElse(null);
        Exercise foundExercise = exerciseDAO.findById(exerciseId).orElse(null);
        if (foundTraining == null) {
            throw new NotFoundException(String.format("Training with id=%d doesn't exist!", trainingId));
        } else if (foundExercise == null) {
            throw new NotFoundException(String.format("Exercise with id=%d doesn't exist!", exerciseId));
        }
        Profile creator = foundTraining.getCreator();
        if (creator == null || creator.getProfileUser().getUsername().equals(clientName)) {
            ExerciseInTraining newExerciseInTraining = new ExerciseInTraining(foundTraining, foundExercise, repetitions, timeSeconds, series);
            exerciseInTrainingDAO.save(newExerciseInTraining); // Duplicate Key
            return newExerciseInTraining;
        } else
            throw new NotFoundException("You can't modify a training, that was created by somebody else!");
    }

    @Override
    public boolean removeExerciseFromTraining(long trainingId, long exerciseId, String clientName) throws NotFoundException {


        ExerciseInTraining ex_in_training =
                exerciseInTrainingDAO.getExerciseInTrainingByExerciseExerciseIdAndTrainingTrainingId(exerciseId, trainingId);
        if (ex_in_training == null)
            throw new NotFoundException("Couldn't find the exercise in training!");
        Profile creator = ex_in_training.getTraining().getCreator();
        if (creator == null || creator.getProfileUser().getUsername().equals(clientName)) {
            boolean removeFromTraining = ex_in_training.getTraining().removeExerciseFromTraining(ex_in_training.getExercise().getExerciseId());
            boolean removeFromExercise = ex_in_training.getExercise().removeTrainingFromExercise(ex_in_training.getTraining().getTrainingId());
            exerciseDAO.save(ex_in_training.getExercise());
            trainingDAO.save(ex_in_training.getTraining());
            System.out.println(removeFromExercise + " " + removeFromTraining);
            exerciseInTrainingDAO.save(ex_in_training);
            exerciseInTrainingDAO.delete(ex_in_training);
            System.out.println("DELETED" + ex_in_training.getId());
            return true;
        } else
            throw new NotFoundException("You can't modify a training, that was created by somebody else!");
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingDAO.findAll();
    }

    @Override
    public Page<Training> getAllTrainings(Pageable pageable, String userName) {
        User clientUser = userDAO.findUserByEmail(userName).orElse(null);
        double weight = 0;
        try {
            weight = clientUser.getProfile().getProfileCard().getWeight();
        } catch (NullPointerException e) {
            System.out.println("User has no profile or profile card!");
        }
        double finalWeight = weight;
        Page<Training> trainingPage = trainingDAO.findAll(pageable);
        trainingPage.getContent().forEach(training -> training.setCaloriesBurned(training.caloriesBurned(finalWeight)));
        return trainingPage;
    }

    @Override
    public Page<Training> getAllTrainingsFiltered(Specification<Training> trainingSpec, Pageable pageable, String userName) throws NotFoundException {
        User clientUser = userDAO.findUserByEmail(userName).orElse(null);
        double weight = 0;
        try {
            weight = clientUser.getProfile().getProfileCard().getWeight();
        } catch (NullPointerException e) {
            System.out.println("User has no profile or profile card!");
        }
        double finalWeight = weight;
        Page<Training> trainingPage = trainingDAO.findAll(trainingSpec, pageable);
        trainingPage.getContent().forEach(training -> {
            training.setCaloriesBurned(training.caloriesBurned(finalWeight));
            training.getExerciseInTrainings().forEach(ex -> ex.setCaloriesBurned(ex.countCaloriesPerExerciseDuration(finalWeight)));
            training.getExerciseInTrainings().forEach(ex -> ex.getExercise().setCaloriesBurned(ex.getExercise().countCaloriesPerHour(finalWeight)));
        });

        return trainingPage;
    }

    @Override
    public List<ExerciseInTraining> getExercisesFromTraining(long trainingId) throws NotFoundException {
        Training training = trainingDAO.findById(trainingId).orElse(null);
        if (training == null) {
            throw new NotFoundException("There's no training with id=" + trainingId);
        }
        return new ArrayList<>(training.getExerciseInTrainings());
    }

    @Override
    public List<Training> getTrainingByCreatorId(UUID creatorId) {
        return trainingDAO.findAllByCreator_Id(creatorId);
    }

    @Override
    public Training updateTraining(Training training) throws NotFoundException {
        Training targetTraining = trainingDAO.findById(training.getTrainingId()).orElse(null);
        if (targetTraining == null)
            throw new NotFoundException("There's no training with id=" + training.getTrainingId());

        trainingDAO.save(training);
        return training;
    }

    @Override
    public Training partialUpdateTraining(Training training) {
        trainingDAO.save(training);
        return training;
    }

    @Override
    public double getCaloriesBurnedFromUser(long trainingPositionId, String userName) throws NotFoundException {

        User user = userDAO.findUserByEmail(userName).orElse(null);
        Training training = trainingDAO.findById(trainingPositionId).orElse(null);
        if (user == null)
            throw new NotFoundException("There's no user with email=" + userName);
        if (training == null)
            throw new NotFoundException("There's no training with id=" + trainingPositionId);

        double weight = user.getProfile().getProfileCard().getWeight();
        System.out.println("User weight: " + weight);
        return training.caloriesBurned(weight);
    }
}
