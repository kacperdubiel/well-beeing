package com.wellbeeing.wellbeeing.domain.sport;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TrainingPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long trainingPositionId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "training_id")
    private Training training;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trainingPlan_id")
    private TrainingPlan trainingPlan;

    @Column(name = "training_date")
    private Date trainingDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ETrainingStatus trainingStatus;

    public TrainingPosition(Training training, TrainingPlan trainingPlan, Date trainingDate) {
        this.training = training;
        this.trainingPlan = trainingPlan;
        this.trainingDate = trainingDate;
        this.trainingStatus = ETrainingStatus.SCRATCH;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrainingPosition)) return false;
        TrainingPosition that = (TrainingPosition) o;
        return Objects.equals(training.getName(), that.training.getName()) &&
                Objects.equals(trainingPlan.getTrainingPlan_id(), that.trainingPlan.getTrainingPlan_id()) &&
                Objects.equals(trainingDate, that.trainingDate) &&
                Objects.equals(trainingStatus, that.trainingStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(training.getName(), trainingPlan.getTrainingPlan_id(), trainingDate, trainingStatus);
    }
}
