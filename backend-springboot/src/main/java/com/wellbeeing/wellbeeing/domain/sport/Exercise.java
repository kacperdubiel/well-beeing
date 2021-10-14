package com.wellbeeing.wellbeeing.domain.sport;

import com.wellbeeing.wellbeeing.domain.Role;
import com.wellbeeing.wellbeeing.domain.SportLabel;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Data
@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long exercise_id;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private EExerciseType exerciseType;
    @Column(name = "description")
    private String description;
    @Column(name = "instruction")
    private String instruction;

    @Column(name = "metabolic_eqv_of_task")
    private float met;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="exercise_labels",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id")
    )
    private Set<SportLabel> labels = new HashSet<>();

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    private Set<ExerciseInTraining> exerciseInTrainings;

    public Exercise(String name, ExerciseInTraining... exerciseInTrainings) {
        this.name = name;
        this.exerciseType = EExerciseType.OTHER;
        for(ExerciseInTraining exerciseInTraining : exerciseInTrainings) exerciseInTraining.setExercise(this);
        this.exerciseInTrainings = Stream.of(exerciseInTrainings).collect(Collectors.toSet());
    }

    public Exercise() {
    }

    public long getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(long exercise_id) {
        this.exercise_id = exercise_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EExerciseType getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(EExerciseType exerciseType) {
        this.exerciseType = exerciseType;
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

    public float getMet() {
        return met;
    }

    public void setMet(float met) {
        this.met = met;
    }
}
