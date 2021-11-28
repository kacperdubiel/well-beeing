package com.wellbeeing.wellbeeing.domain.message.sport;

import com.wellbeeing.wellbeeing.domain.sport.TrainingPlan;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Data
@AllArgsConstructor
public class AddTrainingPlanWithOwnerRequest {
    private TrainingPlan trainingPlan;
    private UUID ownerId;
    private long requestId;

}
