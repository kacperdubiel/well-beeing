package com.wellbeeing.wellbeeing;

import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.domain.sport.*;
import com.wellbeeing.wellbeeing.repository.UserDAO;
import com.wellbeeing.wellbeeing.repository.sport.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@Component
public class MyRunner implements CommandLineRunner {


    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);
    @Autowired
    @Qualifier("exerciseDAO")
    private ExerciseDAO exerciseDAO;
    @Autowired
    @Qualifier("trainingDAO")
    private TrainingDAO trainingDAO;
    @Autowired
    @Qualifier("trainingPlanDAO")
    private TrainingPlanDAO trainingPlanDAO;
    @Autowired
    @Qualifier("userDAO")
    private UserDAO userDAO;
    @Autowired
    @Qualifier("trainingPositionDAO")
    private TrainingPositionDAO trainingPositionDAO;
    @Autowired
    @Qualifier("activityGoalDAO")
    private ActivityGoalDAO activityGoalDAO;

    @Override
    public void run(String... args) throws Exception {
        trainingDAO.deleteAll();
        exerciseDAO.deleteAll();
        trainingPlanDAO.deleteAll();
        trainingPositionDAO.deleteAll();
        activityGoalDAO.deleteAll();
        // Training and exercises
        Training training_a = new Training("Training_A", ETrainingDifficulty.MEDIUM);
        Training training_b = new Training("Training_B", ETrainingDifficulty.HARD);
        trainingDAO.saveAll(Arrays.asList(training_a, training_b));

        Exercise exercise_1 = new Exercise("Exercise_1", 3.4f);
        Exercise exercise_2 = new Exercise("Exercise_2", 5.5f);
        ExerciseInTraining ex_in_tr_1 = new ExerciseInTraining(training_a, exercise_1, 15,20*3600, 1);
        ExerciseInTraining ex_in_tr_2 = new ExerciseInTraining(training_b, exercise_2, 20,30*3600, 1);
        ExerciseInTraining ex_in_tr_3 = new ExerciseInTraining(training_a, exercise_2, 20,30*3600, 3);
        exerciseDAO.save(exercise_1);
        exerciseDAO.save(exercise_2);


        //Training plan
        User abcUser = userDAO.findUserByEmail("abc@abc.com").orElse(null);
        TrainingPlan trainingPlan_1 = new TrainingPlan(abcUser, 2021, 33, "Do details");
        trainingPlanDAO.save(trainingPlan_1);

        trainingPositionDAO.save(new TrainingPosition(training_a, trainingPlan_1, new Date()));
        trainingPositionDAO.save(new TrainingPosition(training_b, trainingPlan_1, new Date()));
        System.out.println("Print trainings " + exercise_1.getExerciseInTrainings());
        System.out.println("Print exercises " + training_a.getExerciseInTrainingSet());
        //Activity goal
        ActivityGoal activityGoal_1 = new ActivityGoal(EGoalType.LOSE_WEIGHT, 10f, "", new Date(2021-1900, Calendar.FEBRUARY, 15), abcUser);
        activityGoalDAO.save(activityGoal_1);

        System.out.println("Calories from exercise 1 for 80kg person " + exercise_1.countCaloriesPerHour(80));
        System.out.println("Calories from training_a for 80kg person " + training_a.caloriesBurned(80));

    }
}
