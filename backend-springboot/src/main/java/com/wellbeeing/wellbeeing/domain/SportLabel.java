package com.wellbeeing.wellbeeing.domain;

import com.wellbeeing.wellbeeing.domain.diet.Ailment;
import com.wellbeeing.wellbeeing.domain.sport.Exercise;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Set;

@Entity
public class SportLabel {
    @Id
    private long sportLabel_id;

    @ManyToMany(mappedBy = "labels")
    private Set<Exercise> exercises;
    @ManyToMany(mappedBy = "allowedLabels")
    private List<Ailment> labeledAilments;
    public SportLabel() {

    }

    public void addLabeledExercise(Exercise exercise) {this.exercises.add(exercise);}
}
