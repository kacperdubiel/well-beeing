package com.wellbeeing.wellbeeing.service.sport.alg;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.diet.Ailment;
import com.wellbeeing.wellbeeing.domain.diet.calculation.ProfileDietCalculation;
import com.wellbeeing.wellbeeing.domain.sport.ActivityGoal;
import com.wellbeeing.wellbeeing.domain.sport.EWorkoutStrategy;

import java.util.Date;
import java.util.List;

public class TrainingPlanGeneratorCSPStrategy implements TrainingPlanGeneratorStrategy {
    @Override
    public long generateTrainingPlan(List<Integer> trainingsPerDay, ActivityGoal goal, Profile profile, EWorkoutStrategy strategy, Date beginningDate) {
        ProfileDietCalculation userCalc = profile.getProfileCard().getDietCalculations();
        List<Ailment> userAilments = profile.getProfileCard().getAilments();

        return -1;
    }
}
