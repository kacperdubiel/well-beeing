package com.wellbeeing.wellbeeing.domain.sport;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
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

    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL)
    private Set<ExerciseInTraining> exerciseInTrainingSet = new HashSet<>();

    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL)
    private Set<TrainingPosition> trainingPlans = new HashSet<>();

    public Training(String name, ETrainingDifficulty difficulty) {
        this.name = name;
        this.trainingDifficulty = difficulty;
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
}
