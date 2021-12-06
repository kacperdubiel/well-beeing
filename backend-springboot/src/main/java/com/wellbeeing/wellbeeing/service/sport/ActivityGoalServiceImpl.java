package com.wellbeeing.wellbeeing.service.sport;

import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.sport.ActivityGoal;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.repository.sport.ActivityGoalDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("activityGoalService")
public class ActivityGoalServiceImpl implements ActivityGoalService {

    private final ActivityGoalDAO activityGoalDAO;
    private final UserDAO userDAO;

    public ActivityGoalServiceImpl(@Qualifier("activityGoalDAO") ActivityGoalDAO activityGoalDAO,
                                   @Qualifier("userDAO") UserDAO userDAO) {
        this.activityGoalDAO = activityGoalDAO;
        this.userDAO = userDAO;
    }

    @Override
    public List<ActivityGoal> getMyActivityGoals(String userName) throws NotFoundException {
        User owner = userDAO.findUserByEmail(userName).orElse(null);
        if (owner == null)
            throw new NotFoundException(String.format("No user with username %s", userName));
        return activityGoalDAO.findActivityGoalsByOwner(owner.getProfile());
    }

    @Override
    public ActivityGoal addActivityGoal(ActivityGoal newGoal, String userName) throws NotFoundException {
        User owner = userDAO.findUserByEmail(userName).orElse(null);
        if (owner == null)
            throw new NotFoundException(String.format("No user with username %s", userName));
        newGoal.setOwner(owner.getProfile());
        return activityGoalDAO.save(newGoal);
    }

    @Override
    public ActivityGoal getActivityGoal(long goalId, String userName) throws NotFoundException {
        User owner = userDAO.findUserByEmail(userName).orElse(null);
        if (owner == null)
            throw new NotFoundException(String.format("No user with username %s", userName));
        return activityGoalDAO.findActivityGoalByActivityGoalIdAndOwner(goalId, owner.getProfile());
    }
}
