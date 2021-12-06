package com.wellbeeing.wellbeeing.service.sport;

import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.sport.ActivityGoal;

import java.util.List;

public interface ActivityGoalService {
    List<ActivityGoal> getMyActivityGoals(String userName) throws NotFoundException;

    ActivityGoal addActivityGoal(ActivityGoal newGoal, String userName) throws NotFoundException;

    ActivityGoal getActivityGoal(long goalId, String userName) throws NotFoundException;
}
