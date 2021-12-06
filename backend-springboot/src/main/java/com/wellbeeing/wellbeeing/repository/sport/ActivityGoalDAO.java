package com.wellbeeing.wellbeeing.repository.sport;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.sport.ActivityGoal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityGoalDAO extends JpaRepository<ActivityGoal, Long> {
    List<ActivityGoal> findActivityGoalsByOwner(Profile owner);

    ActivityGoal findActivityGoalByActivityGoalIdAndOwner(long activityGoalId, Profile owner);
}
