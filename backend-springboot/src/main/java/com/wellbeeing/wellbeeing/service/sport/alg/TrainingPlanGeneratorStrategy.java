package com.wellbeeing.wellbeeing.service.sport.alg;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.sport.ActivityGoal;

import java.util.List;

public interface TrainingPlanGeneratorStrategy {
    void generateTrainingPlan(List<Integer> trainingsPerDay, ActivityGoal goal, Profile profile, int caloriesToBeBurned);
}
