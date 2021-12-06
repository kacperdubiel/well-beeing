package com.wellbeeing.wellbeeing.domain.sport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;
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

    @Transient
    private String dayOfTheWeek;

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

    @PostLoad
    public void OnLoad() {
        LocalDate localDate = trainingDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        String dayName = localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
//        dayOfTheWeek = dayName; // ENGLISH
        switch (dayName) {
            case "Monday":
                dayOfTheWeek = "Poniedziałek";
                break;
            case "Tuesday":
                dayOfTheWeek = "Wtorek";
                break;
            case "Wednesday":
                dayOfTheWeek = "Środa";
                break;
            case "Thursday":
                dayOfTheWeek = "Czwartek";
                break;
            case "Friday":
                dayOfTheWeek = "Piątek";
                break;
            case "Saturday":
                dayOfTheWeek = "Sobota";
                break;
            case "Sunday":
                dayOfTheWeek = "Niedziela";
                break;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainingPositionId);
    }

}
