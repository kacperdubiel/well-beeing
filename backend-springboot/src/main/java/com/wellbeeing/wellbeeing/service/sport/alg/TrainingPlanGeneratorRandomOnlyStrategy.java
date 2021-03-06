package com.wellbeeing.wellbeeing.service.sport.alg;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.IllegalArgumentException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.sport.*;
import com.wellbeeing.wellbeeing.repository.sport.TrainingDAO;
import com.wellbeeing.wellbeeing.repository.sport.TrainingPlanDAO;
import com.wellbeeing.wellbeeing.service.sport.TrainingPlanService;
import com.wellbeeing.wellbeeing.service.sport.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class TrainingPlanGeneratorRandomOnlyStrategy implements TrainingPlanGeneratorStrategy {
    TrainingDAO trainingDAO;
    TrainingPlanDAO trainingPlanDAO;
    TrainingPlanService trainingPlanService;
    TrainingService trainingService;
    int MIN_TRAINING_CALORIES = 200;
    int MAX_TRAINING_CALORIES = 2000;
    @Autowired
    private EntityManager entityManager;

    @Autowired
    public TrainingPlanGeneratorRandomOnlyStrategy(@Qualifier("trainingDAO") TrainingDAO trainingDAO,
                                                   @Qualifier("trainingPlanDAO") TrainingPlanDAO trainingPlanDAO,
                                                   @Lazy @Qualifier("trainingPlanService") TrainingPlanService trainingPlanService,
                                                   @Qualifier("trainingService") TrainingService trainingService) {
        this.trainingDAO = trainingDAO;
        this.trainingPlanService = trainingPlanService;
        this.trainingService = trainingService;
        this.trainingPlanDAO = trainingPlanDAO;
    }

    @Override
    @Transactional
    public long generateTrainingPlan(List<Integer> trainingsPerDay, ActivityGoal goal, Profile profile, EWorkoutStrategy strategy, Date beginningDate) throws NotFoundException, IllegalArgumentException {
        String userName = profile.getProfileUser().getEmail();

        int totalTrainings = trainingsPerDay.stream().mapToInt(Integer::intValue).sum();

        System.out.printf("TotalTrainings: %d,%n", totalTrainings);
//      Store trainings that are made only of exercises with labels not connected with user's ailments
        List<Training> trainings = trainingService.getAllTrainings(userName);
//        && t.getCaloriesBurned() >= averageCaloriesBurnedPerTraining * (1 - deviation)
//                && t.getCaloriesBurned() <= averageCaloriesBurnedPerTraining * (1 + deviation)
        if (trainings.size() == 0) {
            System.out.println("No trainings matching criteria");
            return -1;
        }

        LocalDate start = beginningDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().with(DayOfWeek.MONDAY);

        TrainingPlan newTrainingPlan = TrainingPlan.builder()
                .owner(profile)
                .creator(profile)
                .details("Randomly generated plan")
                .beginningDate(Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        newTrainingPlan = trainingPlanService.addTrainingPlan(newTrainingPlan, userName, profile.getProfileUser().getId(), 0);
        newTrainingPlan.setPlanStatus(EPlanStatus.PLANNED);
        newTrainingPlan = trainingPlanDAO.save(newTrainingPlan);
        for (int i = 0; i < trainingsPerDay.size(); i++) {
            Date currentDate = Date.from(start.plusDays(i).atStartOfDay(ZoneId.systemDefault()).toInstant());
            for (int j = 0; j < trainingsPerDay.get(i); j++) {
                Training training = trainings.get((int) (Math.random() * trainings.size()));
                trainingPlanService.addPositionToTrainingPlan(newTrainingPlan.getTrainingPlanId(),
                        training.getTrainingId(),
                        currentDate,
                        "MORNING",
                        userName);
            }
        }
        return newTrainingPlan.getTrainingPlanId();
    }
}
