package com.wellbeeing.wellbeeing.service.sport.alg;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.IllegalArgumentException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.sport.ActivityGoal;
import com.wellbeeing.wellbeeing.domain.sport.EWorkoutStrategy;

import java.util.Date;
import java.util.List;

public interface TrainingPlanGeneratorStrategy {
    int CALORIES_TO_BURN_1KG = 7700;
    int CALORIES_TO_GAIN_1KG_MUSCLE = 2800;

    long generateTrainingPlan(List<Integer> trainingsPerDay, ActivityGoal goal, Profile profile, EWorkoutStrategy strategy, Date beginningDate) throws NotFoundException, IllegalArgumentException;
}
