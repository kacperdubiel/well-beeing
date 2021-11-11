package com.wellbeeing.wellbeeing.domain.sport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.diet.Dish;
import com.wellbeeing.wellbeeing.domain.diet.NutritionalValueDerivable;
import com.wellbeeing.wellbeeing.domain.diet.NutritionalValueDerivedData;
import com.wellbeeing.wellbeeing.domain.diet.Report;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private double seconds;
    @Transient
    private double minutes;
    @Transient
    private double hours;
    @Transient
    private int caloriesBurned;

    @PostLoad
    private void setTimePostLoad(){
        this.setMinutes(this.seconds/60);
        this.setHours(this.seconds/3600);
        this.countCalories();
    }

    public void countCalories(){
        this.caloriesBurned = (int) (this.getExercise().countCaloriesPerHour(this.getSportReport().getReportOwner().getProfileCard().getWeight())
                        * this.getHours());
    }



}
