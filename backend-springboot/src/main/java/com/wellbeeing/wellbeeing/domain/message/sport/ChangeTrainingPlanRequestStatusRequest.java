package com.wellbeeing.wellbeeing.domain.message.sport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeTrainingPlanRequestStatusRequest {
    private long requestId;
    private String newStatus;
}
