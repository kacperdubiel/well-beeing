package com.wellbeeing.wellbeeing.domain.sport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"exercise_id", "training_id"})})
@Entity
public class ExerciseInTraining {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "training_id")
    @JsonIgnore
    private Training training;
    @Column(name = "reps")
    private int repetitions;
    @Column(name = "series")
    private Integer series;
    @Column(name = "time_in_seconds")
    private int time_seconds;

    @Transient
    private int caloriesBurned;

    public ExerciseInTraining(Training training, Exercise exercise, int repetitions, int time_seconds, int series) {
        this.training = training;
        this.exercise = exercise;
        this.repetitions = repetitions;
        this.time_seconds = time_seconds;
        this.series = series;
        this.training.addExerciseToTraining(this);
        this.exercise.addTrainingToExercise(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExerciseInTraining)) return false;
        ExerciseInTraining that = (ExerciseInTraining) o;
        return Objects.equals(training.getName(), that.training.getName()) &&
                Objects.equals(exercise.getName(), that.exercise.getName()) &&
                Objects.equals(repetitions, that.repetitions) &&
                Objects.equals(time_seconds, that.time_seconds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, repetitions, time_seconds);
    }

    public int countCaloriesPerExerciseDuration(double user_weight) {
        //METs x 3.5 x (your body weight in kilograms) / 200 = calories burned per minute
     return (int) ((time_seconds*series)/60d*(exercise.getMet()*3.5*user_weight/200));
    }

    @Override
    public String toString() {
        return "ExerciseInTraining{" +
                "id=" + id +
                ", exercise=" + exercise +
                ", training=" + training +
                ", repetitions=" + repetitions +
                ", time_seconds=" + time_seconds +
                '}';
    }
}
