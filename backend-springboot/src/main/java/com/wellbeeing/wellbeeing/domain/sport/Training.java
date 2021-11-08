package com.wellbeeing.wellbeeing.domain.sport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.SportLabel;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
@Getter
@Setter
@NoArgsConstructor
@Data
@Entity
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long trainingId;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty", nullable = false)
    private ETrainingDifficulty trainingDifficulty;
    @Column(name = "description")
    private String description;
    @Column(name = "instruction")
    private String instruction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator")
    @JsonIgnore
    private Profile creator;

    @Transient
    private int caloriesBurned;

    @Transient
    private int totalTrainingTimeSeconds;

    @Column(name = "isPrivate")
    private boolean isPrivate = false;

    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL)
    private Set<ExerciseInTraining> exerciseInTrainings = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL)
    private Set<TrainingPosition> trainingPlans = new HashSet<>();

    public Training(String name, ETrainingDifficulty difficulty) { //, ExerciseInTraining... exerciseInTrainings
        this.name = name;
        this.trainingDifficulty = difficulty;
        this.exerciseInTrainings = new HashSet<>();
    }

    public int caloriesBurned(double user_weight) {
        return this.exerciseInTrainings.stream().map(ex -> ex.countCaloriesPerExerciseDuration(user_weight)).mapToInt(num -> num).sum();
    }
//    @Transient
//    public int getTotalTrainingTimeSeconds() {
//        return this.exerciseInTrainings.stream().map(ex -> ex.getTime_seconds()*ex.getSeries()).mapToInt(num -> num).sum();
//    }

    @PostLoad
    public void onTotalTrainingTimeSeconds() {
        this.totalTrainingTimeSeconds =  this.exerciseInTrainings.stream().map(ex -> ex.getTime_seconds()*ex.getSeries()).mapToInt(num -> num).sum();
    }

    @Override
    public String toString() {
        return "Training{" +
                "training_id=" + trainingId +
                ", name='" + name + '\'' +
                ", trainingDifficulty=" + trainingDifficulty +
                ", description='" + description + '\'' +
                '}';
    }

    public void addExerciseToTraining(ExerciseInTraining exercise) {
        exerciseInTrainings.add(exercise);
    }

    public boolean removeExerciseFromTraining(long exerciseId) {
        return exerciseInTrainings.removeIf(e->e.getExercise().getExerciseId() == exerciseId);
    }

    public Set<SportLabel> getLabels() {
        Set<SportLabel> labels = new HashSet<>();
        exerciseInTrainings.forEach(e -> labels.addAll(e.getExercise().getLabels()));
        return labels;
    }
}
