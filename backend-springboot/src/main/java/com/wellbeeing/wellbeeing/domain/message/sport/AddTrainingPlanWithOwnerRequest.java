package com.wellbeeing.wellbeeing.domain.message.sport;

import com.wellbeeing.wellbeeing.domain.sport.TrainingPlan;
import lombok.NoArgsConstructor;

import java.util.UUID;
@NoArgsConstructor
public class AddTrainingPlanWithOwnerRequest {
    private TrainingPlan trainingPlan;
    private UUID ownerId;

    public AddTrainingPlanWithOwnerRequest(TrainingPlan trainingPlan, UUID ownerId) {
        this.trainingPlan = trainingPlan;
        this.ownerId = ownerId;
    }

    public TrainingPlan getTrainingPlan() {
        return trainingPlan;
    }

    public UUID getOwnerId() {
        return ownerId;
    }
}
