package com.wellbeeing.wellbeeing.domain.message.sport;

import java.util.Date;

public class AddTrainingToPlanRequest {
    private long trainingId;
    private long trainingPlanId;
    private Date date;

    public AddTrainingToPlanRequest(long trainingId, long trainingPlanId, Date date) {
        this.trainingId = trainingId;
        this.trainingPlanId = trainingPlanId;
        this.date = date;
    }

    public long getTrainingId() {
        return trainingId;
    }

    public long getTrainingPlanId() {
        return trainingPlanId;
    }

    public Date getDate() {
        return date;
    }
}
