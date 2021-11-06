package com.wellbeeing.wellbeeing.domain.message.sport;

import lombok.Data;

import java.util.Date;
@Data
public class AddTrainingToPlanRequest {
    private long trainingId;
    private long trainingPlanId;
    private Date date;
    private String timeOfDay;

}
