package com.wellbeeing.wellbeeing.domain.sport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.diet.ReportDishDetail;
import com.wellbeeing.wellbeeing.domain.diet.ReportProductDetail;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SportReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "owner_profile_id")
    @JsonIgnore
    private Profile reportOwner;
    @Column
    private LocalDate reportDate;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "sportReport", cascade = CascadeType.ALL, orphanRemoval=true)
    List<ReportExercise> exerciseList = new ArrayList<>();
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "sportReport", cascade = CascadeType.ALL, orphanRemoval=true)
    List<ReportTraining> trainingList = new ArrayList<>();
    @Transient
    private int caloriesBurned;
    @Transient
    private int totalTimeSeconds;

    public void removeReportExerciseFromReport(ReportExercise reportExercise){
        exerciseList.remove(reportExercise);
    }
    public void removeReportTrainingFromReport(ReportTraining reportTraining){
        trainingList.remove(reportTraining);
    }

    public void preUpdate() {
        countTotalCalories();
        countTotalSeconds();
    }

    public void countTotalCalories(){
        this.exerciseList.forEach(ReportExercise::countCalories);
        this.trainingList.forEach(ReportTraining::countCalories);
        this.caloriesBurned = this.exerciseList.stream().map(ReportExercise::getCaloriesBurned).mapToInt(num -> num).sum() +
                this.trainingList.stream().map(ReportTraining::getCaloriesBurned).mapToInt(num -> num).sum();
    }

    public void countTotalSeconds(){
        this.totalTimeSeconds = this.exerciseList.stream().map(ReportExercise::getSeconds).mapToInt(num -> num).sum() +
                this.trainingList.stream().map(ReportTraining::getSeconds).mapToInt(num -> num).sum();
    }
}
