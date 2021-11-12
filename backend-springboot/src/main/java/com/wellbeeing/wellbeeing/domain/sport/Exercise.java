package com.wellbeeing.wellbeeing.domain.sport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.SportLabel;
import lombok.*;
import com.wellbeeing.wellbeeing.domain.account.Role;
//import com.wellbeeing.wellbeeing.domain.SportLabel;
import com.wellbeeing.wellbeeing.domain.account.User;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Data
@Entity
@Builder
@AllArgsConstructor
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long exerciseId;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private EExerciseType exerciseType = EExerciseType.OTHER;
    @Column(name = "description", columnDefinition="TEXT")
    private String description;
    @Column(name = "instruction", columnDefinition="TEXT")
    private String instruction;
    @Column(name = "isPrivate")
    private boolean isPrivate = false;
    @Column(name = "metabolic_eqv_of_task")
    private double met;

    @Transient
    private int caloriesBurned;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator")
    @JsonIgnore
    private Profile creator;
    @JsonProperty
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="exercise_labels",
            joinColumns = @JoinColumn(name = "exerciseId"),
            inverseJoinColumns = @JoinColumn(name = "sportLabelId")
    )
    private Set<SportLabel> labels = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ExerciseInTraining> exerciseInTrainings;
    @Column
    private String pathToVideoInstruction;

    public Exercise(String name, double met) { //, ExerciseInTraining... exerciseInTrainings
        this.name = name;
        this.met = met;
        this.exerciseType = EExerciseType.OTHER;
        this.exerciseInTrainings = new HashSet<>();
//        for(ExerciseInTraining exerciseInTraining : exerciseInTrainings) exerciseInTraining.setExercise(this);
//        this.exerciseInTrainings = Stream.of(exerciseInTrainings).collect(Collectors.toSet());
    }

    public int countCaloriesPerHour(double user_weight) {
        //METs x 3.5 x (your body weight in kilograms) / 200 = calories burned per minute
        return (int) (60*(met*3.5*user_weight/200));
    }

    public void addTrainingToExercise(ExerciseInTraining training) {
        exerciseInTrainings.add(training);
    }

    public boolean removeTrainingFromExercise(long trainingId) {
        return exerciseInTrainings.removeIf(e->e.getTraining().getTrainingId() == trainingId);
    }

    public void addLabelToExercise(SportLabel sportLabel) {this.labels.add(sportLabel);}
    @Override
    public String toString() {
        return "Exercise{" +
                "exercise_id=" + exerciseId +
                ", name='" + name + '\'' +
                ", exerciseType=" + exerciseType +
                ", description='" + description + '\'' +
                ", met=" + met +
                '}';
    }

}
