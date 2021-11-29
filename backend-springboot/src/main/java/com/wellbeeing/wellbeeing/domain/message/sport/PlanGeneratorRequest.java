package com.wellbeeing.wellbeeing.domain.message.sport;

import com.wellbeeing.wellbeeing.domain.sport.EWorkoutStrategy;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanGeneratorRequest {
    private List<Integer> trainingsPerDay; // 7 positions
    private Date beginningDate;
    private long activityGoalId;
    private EWorkoutStrategy strategy;

}
