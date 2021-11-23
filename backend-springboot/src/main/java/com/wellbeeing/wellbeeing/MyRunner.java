package com.wellbeeing.wellbeeing;

import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.repository.account.ProfileCardDAO;
import com.wellbeeing.wellbeeing.repository.account.ProfileDAO;
import com.wellbeeing.wellbeeing.repository.account.TrainerProfileDAO;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.repository.sport.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
    @Autowired
    @Qualifier("profileDAO")
    private ProfileDAO profileDAO;
    @Autowired
    @Qualifier("trainerProfileDAO")
    private TrainerProfileDAO trainerProfileDAO;
    @Autowired
    @Qualifier("exerciseInTrainingDAO")
    private ExerciseInTrainingDAO exerciseInTrainingDAO;
    @Autowired
    @Qualifier("profileCardDAO")
    private ProfileCardDAO profileCardDAO;
    @Autowired
    @Qualifier("sportLabelDAO")
    private SportLabelDAO sportLabelDAO;

    @Override
    public void run(String... args) throws Exception {
//        trainingPositionDAO.deleteAll();
//        trainingPlanDAO.deleteAll();
//        activityGoalDAO.deleteAll();
//        exerciseInTrainingDAO.deleteAll();
//        profileCardDAO.deleteAll();
//        trainingDAO.deleteAll();
//        exerciseDAO.deleteAll();
//        profileDAO.deleteAll();
//        trainerDAO.deleteAll();
        // Training and exercises
//        Training training_a = new Training("Training_A", ETrainingDifficulty.MEDIUM);
//        Training training_b = new Training("Training_B", ETrainingDifficulty.HARD);
//        trainingDAO.saveAll(Arrays.asList(training_a, training_b));
//
//        Exercise exercise_1 = new Exercise("Exercise_1", 3.4d);
//        Exercise exercise_2 = new Exercise("Exercise_2", 5.5d);
//        exerciseDAO.save(exercise_1);
//        exerciseDAO.save(exercise_2);
//        ExerciseInTraining ex_in_tr_1 = new ExerciseInTraining(training_a, exercise_1, 15,20*3600, 1);
//        ExerciseInTraining ex_in_tr_2 = new ExerciseInTraining(training_b, exercise_2, 20,30*3600, 1);
//        ExerciseInTraining ex_in_tr_3 = new ExerciseInTraining(training_a, exercise_2, 20,30*3600, 3);
//
//        exerciseInTrainingDAO.saveAll(Arrays.asList(ex_in_tr_1, ex_in_tr_2, ex_in_tr_3));
//        //Training plan
//        User abcUser = userDAO.findUserByEmail("abc@abc.com").orElse(null);
//        User aaaUser = userDAO.findUserByEmail("aaa@aaaa.pl").orElse(null);
//        System.out.println(abcUser);
//        assert abcUser != null;
//        Profile abcUserProfile = new Profile("Aaa", "Bbb", new Date(), ESportTag.NONE, ENutritionTag.NONE, abcUser);
//        profileDAO.save(abcUserProfile);
//        ProfileCard abcUserProfileCard = new ProfileCard(120, 21, abcUserProfile);
//        abcUserProfile.setProfileCard(abcUserProfileCard);
//        profileCardDAO.save(abcUserProfileCard);
//        profileDAO.save(abcUserProfile);
//        TrainerProfile trainerAbcUserProfile = new TrainerProfile(abcUserProfile);
//        trainerDAO.save(trainerAbcUserProfile);
//
//        TrainingPlan trainingPlan_1 = new TrainingPlan(abcUserProfile, 2021, 33, "Do details", abcUserProfile);
//        trainingPlanDAO.save(trainingPlan_1);
//
//        trainingPositionDAO.save(new TrainingPosition(training_a, trainingPlan_1, new Date()));
//        trainingPositionDAO.save(new TrainingPosition(training_b, trainingPlan_1, new Date()));
//        System.out.println("Print trainings " + exercise_1.getExerciseInTrainings());
//        System.out.println("Print exercises " + training_a.getExerciseInTrainings());
//        //Activity goal
//        ActivityGoal activityGoal_1 = new ActivityGoal(EGoalType.LOSE_WEIGHT, 10d, "", new Date(2021-1900, Calendar.FEBRUARY, 15), abcUserProfile);
//        activityGoalDAO.save(activityGoal_1);
//
//        System.out.println("Calories from exercise 1 for 80kg person " + exercise_1.countCaloriesPerHour(80));
//        System.out.println("Calories from training_a for 80kg person " + training_a.caloriesBurned(80));

//        training_a.removeExerciseFromTraining(exercise_1.getExercise_id());
//        exercise_1.removeTrainingFromExercise(training_a.getTraining_id());
//        trainingDAO.save(training_a);
//        exerciseDAO.save(exercise_1);
//        exerciseInTrainingDAO.delete(exerciseInTrainingDAO.getExerciseInTrainingByExerciseAndTraining(exercise_1,training_a));

//        Potrzebne utworzone profile!!
//        System.out.println("--------------------------------------");
        User klaudia = userDAO.findUserByEmail("klaudia@").orElse(null);
//        System.out.println("Klaudia" + klaudia);
//        assert klaudia != null;
//        klaudia.getRoles().forEach(r -> System.out.println(r.getRole()));
        System.out.println(klaudia.getAuthorities());
//        System.out.println(klaudia.getRoles());
//        System.out.println(klaudia.getProfile().getFirstName());
//        System.out.println(klaudia.getProfile().getLastName());


//        Profile klaudiaUserProfile = new Profile("Klaudia", "Laskowska", new Date(), ESportTag.NONE, ENutritionTag.NONE, klaudia);
//        profileDAO.save(klaudiaUserProfile);
    }
}
