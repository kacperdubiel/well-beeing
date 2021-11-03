package com.wellbeeing.wellbeeing.domain.sport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class TrainingPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long trainingPositionId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "training_id")
    private Training training;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "trainingPlan_id")
    @JsonIgnore
    private TrainingPlan trainingPlan;

    @Column(name = "training_date")
    private Date trainingDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ETrainingStatus trainingStatus = ETrainingStatus.SCRATCH;
    @Enumerated(EnumType.STRING)
    @Column(name = "time_of_day")
    private ETimeOfDay timeOfDay = ETimeOfDay.EVENING;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrainingPosition)) return false;
        TrainingPosition that = (TrainingPosition) o;
        return Objects.equals(training.getName(), that.training.getName()) &&
                Objects.equals(trainingPlan.getTrainingPlanId(), that.trainingPlan.getTrainingPlanId()) &&
                Objects.equals(trainingDate, that.trainingDate) &&
                Objects.equals(trainingStatus, that.trainingStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(training.getName(), trainingPlan.getTrainingPlanId(), trainingDate, trainingStatus);
    }

}
