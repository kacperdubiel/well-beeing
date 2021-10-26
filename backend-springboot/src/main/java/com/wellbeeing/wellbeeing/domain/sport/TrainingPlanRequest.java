package com.wellbeeing.wellbeeing.domain.sport;


import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.account.TrainerProfile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TrainingPlanRequest {
    @Id
    @GeneratedValue
    @Column(name = "request_id")
    private Long id;

    @ManyToOne
    private Profile submitter;

    @ManyToOne
    private TrainerProfile trainer;

    @Column
    private String message;

    @Column
    private Date requestDate = new Date();
    @Enumerated(EnumType.STRING)
    @Column
    private ERequestStatus requestStatus = ERequestStatus.SUBMITTED;

    public TrainingPlanRequest(Profile submitter, TrainerProfile trainer, String message) {
        this.submitter = submitter;
        this.trainer = trainer;
        this.message = message;
    }


    public void setStatus(String status) {
        this.requestStatus = ERequestStatus.valueOf(status.toUpperCase());
    }
}
