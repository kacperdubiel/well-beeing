package com.wellbeeing.wellbeeing.domain.sport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.mapstruct.control.DeepClone;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DeepClone
public class ReportExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "exercise_id", referencedColumnName = "exerciseId")
    private Exercise exercise;
    @ManyToOne
    @JoinColumn(name = "report_id", referencedColumnName = "id")
    @JsonIgnore
    private SportReport sportReport;
    @Column
    private LocalDateTime exercisingTime;
    @Column
    private int seconds;
    @Transient
    private double minutes;
    @Transient
    private double hours;
    @Transient
    private int caloriesBurned;

    @PostLoad
    private void setTimePostLoad(){
        this.setMinutes((double)this.seconds/60);
        this.setHours((double)this.seconds/3600);
//        this.countCalories();
    }

    public void countCalories(){
        this.caloriesBurned = (int) (this.getExercise().countCaloriesPerHour(this.getSportReport().getReportOwner().getProfileCard().getWeight())
                        * this.getHours());
    }



}
