package com.wellbeeing.wellbeeing.domain.sport;

import com.wellbeeing.wellbeeing.domain.SportLabel;
import com.wellbeeing.wellbeeing.domain.account.Role;
//import com.wellbeeing.wellbeeing.domain.SportLabel;
import com.wellbeeing.wellbeeing.domain.account.User;
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

    public Exercise(String name, float met) { //, ExerciseInTraining... exerciseInTrainings
        this.name = name;
        this.met = met;
        this.exerciseType = EExerciseType.OTHER;
        this.exerciseInTrainings = new HashSet<>();
//        for(ExerciseInTraining exerciseInTraining : exerciseInTrainings) exerciseInTraining.setExercise(this);
//        this.exerciseInTrainings = Stream.of(exerciseInTrainings).collect(Collectors.toSet());
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

    public Set<SportLabel> getLabels() {
        return labels;
    }

    public void setLabels(Set<SportLabel> labels) {
        this.labels = labels;
    }

    public Set<ExerciseInTraining> getExerciseInTrainings() {
        return exerciseInTrainings;
    }

    public void setExerciseInTrainings(Set<ExerciseInTraining> exerciseInTrainings) {
        this.exerciseInTrainings = exerciseInTrainings;
    }

    public int countCaloriesPerHour(int user_weight) {
        //METs x 3.5 x (your body weight in kilograms) / 200 = calories burned per minute
        return (int) (60*(met*3.5*user_weight/200));
    }

    public void addTrainingToExercise(ExerciseInTraining training) {
        exerciseInTrainings.add(training);
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "exercise_id=" + exercise_id +
                ", name='" + name + '\'' +
                ", exerciseType=" + exerciseType +
                ", description='" + description + '\'' +
                ", met=" + met +
                '}';
    }
}
