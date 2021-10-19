package com.wellbeeing.wellbeeing.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.diet.Ailment;
import com.wellbeeing.wellbeeing.domain.sport.Exercise;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Set;
@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
public class SportLabel {
    @Id
    private long sportLabel_id;

    @Column(name = "name")
    private String name;
    @JsonIgnore
    @ManyToMany(mappedBy = "labels")
    private Set<Exercise> exercises;
    @ManyToMany(mappedBy = "allowedLabels")
    private List<Ailment> labeledAilments;

    public void addLabeledExercise(Exercise exercise) {this.exercises.add(exercise);}
}
