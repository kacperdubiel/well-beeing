package com.wellbeeing.wellbeeing.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.diet.Ailment;
import com.wellbeeing.wellbeeing.domain.sport.Exercise;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
public class SportLabel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sportLabel_Id")
    private long sportLabelId;

    @Column(name = "name")
    private String name;
    @JsonIgnore
    @ManyToMany(mappedBy = "labels")
    private Set<Exercise> exercises;
    @ManyToMany(mappedBy = "allowedLabels")
    private List<Ailment> labeledAilments;

    public void addLabeledExercise(Exercise exercise) {this.exercises.add(exercise);}
}

