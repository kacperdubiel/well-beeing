package com.wellbeeing.wellbeeing.domain.sport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long training_id;
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
    private Profile creator;
    @JsonIgnore
    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL)
    private Set<ExerciseInTraining> exerciseInTrainings = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL)
    private Set<TrainingPosition> trainingPlans = new HashSet<>();

    public Training(String name, ETrainingDifficulty difficulty) { //, ExerciseInTraining... exerciseInTrainings
        this.name = name;
        this.trainingDifficulty = difficulty;
        this.exerciseInTrainings = new HashSet<>();
//        for(ExerciseInTraining exerciseInTraining : exerciseInTrainings) exerciseInTraining.setTraining(this);
//        this.exerciseInTrainings = Stream.of(exerciseInTrainings).collect(Collectors.toSet());
    }

    public Training() {

    }

    public long getTraining_id() {
        return training_id;
    }

    public void setTraining_id(long training_id) {
        this.training_id = training_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ETrainingDifficulty getTrainingDifficulty() {
        return trainingDifficulty;
    }

    public void setTrainingDifficulty(ETrainingDifficulty trainingDifficulty) {
        this.trainingDifficulty = trainingDifficulty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public int caloriesBurned(double user_weight) {
        return this.exerciseInTrainings.stream().map(ex -> ex.countCaloriesPerExerciseDuration(user_weight)).mapToInt(num -> num).sum();
    }

    @Override
    public String toString() {
        return "Training{" +
                "training_id=" + training_id +
                ", name='" + name + '\'' +
                ", trainingDifficulty=" + trainingDifficulty +
                ", description='" + description + '\'' +
                '}';
    }

    public void setExerciseInTrainingSet(Set<ExerciseInTraining> exerciseInTrainingSet) {
        this.exerciseInTrainings = exerciseInTrainingSet;
    }

    public void addExerciseToTraining(ExerciseInTraining exercise) {
        exerciseInTrainings.add(exercise);
    }

    public boolean removeExerciseFromTraining(long exerciseId) {
        return exerciseInTrainings.removeIf(e->e.getExercise().getExercise_id() == exerciseId);
    }

    public Profile getCreator() {
        return creator;
    }

    public void setCreator(Profile creator) {
        this.creator = creator;
    }

    public Set<ExerciseInTraining> getExerciseInTrainings() {
        return exerciseInTrainings;
    }

    public void setExerciseInTrainings(Set<ExerciseInTraining> exerciseInTrainings) {
        this.exerciseInTrainings = exerciseInTrainings;
    }
}
