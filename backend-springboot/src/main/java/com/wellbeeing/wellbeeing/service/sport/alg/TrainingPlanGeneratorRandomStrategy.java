package com.wellbeeing.wellbeeing.service.sport.alg;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.diet.Ailment;
import com.wellbeeing.wellbeeing.domain.diet.calculation.ProfileDietCalculation;
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
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrainingPlanGeneratorRandomStrategy implements TrainingPlanGeneratorStrategy {
    TrainingDAO trainingDAO;
    TrainingPlanDAO trainingPlanDAO;
    TrainingPlanService trainingPlanService;
    TrainingService trainingService;
    @Autowired
    private EntityManager entityManager;

    @Autowired
    public TrainingPlanGeneratorRandomStrategy(@Qualifier("trainingDAO") TrainingDAO trainingDAO,
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
        ProfileDietCalculation userCalc = profile.getProfileCard().getDietCalculations();
        double weight = profile.getProfileCard().getWeight();
        double metabolism = userCalc.getBasicMetabolism();
        String userName = profile.getProfileUser().getEmail();
        List<Ailment> userAilments = profile.getProfileCard().getAilments();
        double intensity = goal.getGoalType().getIntensity();
        int weeklyCaloriesToBurn = 0;
        switch (goal.getGoalType()) {
            case LOSE_WEIGHT:
                //kg per week
                weeklyCaloriesToBurn += goal.getNumericValue() * CALORIES_TO_BURN_1KG;
                break;
            case GAIN_WEIGHT:
                //kg per week
                weeklyCaloriesToBurn -= goal.getNumericValue() * CALORIES_TO_BURN_1KG;
                break;
            case BUILD_MUSCLE:
                double muscleGrowthAsPercentOfBodyWeight = (1.8 - profile.getProfileCard().getActivityLevel().getPalFactor()) * weight;
                weeklyCaloriesToBurn -= muscleGrowthAsPercentOfBodyWeight * CALORIES_TO_GAIN_1KG_MUSCLE;
                break;
            case MAINTAIN_WEIGHT:
                break;
            case EXTEND_LUNGS_CAPACITY:
        }
        weeklyCaloriesToBurn = (int) (weeklyCaloriesToBurn * strategy.getWorkoutWeight());
        int totalTrainings = trainingsPerDay.stream().mapToInt(Integer::intValue).sum();
        int averageCaloriesBurnedPerTraining = weeklyCaloriesToBurn / totalTrainings;
        double deviation = 0.2;
        System.out.printf("WeeklyCalories: %d, TotalTrainings: %d, AvgCaloriesPerTraining: %d%n",
                weeklyCaloriesToBurn, totalTrainings, averageCaloriesBurnedPerTraining);
//      Store trainings that are made only of exercises with labels not connected with user's ailments
        List<Training> trainings = trainingService.getAllTrainings(userName)
                .stream().
                filter(t ->
                        t.getLabels()
                                .stream()
                                .allMatch(l -> Collections.
                                        disjoint(l.getLabeledAilments()
                                                        .stream()
                                                        .map(SportLabelAilment::getAilment)
                                                        .collect(Collectors.toList())
                                                , userAilments))

                )
                .collect(Collectors.toList());
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
