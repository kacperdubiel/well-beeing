package com.wellbeeing.wellbeeing.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.diet.Ailment;
import com.wellbeeing.wellbeeing.domain.sport.Exercise;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SportLabel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sportLabel_Id")
    private long sportLabelId;

    @Column(name = "name")
    private String name;
    @JsonIgnore
    @ManyToMany(mappedBy = "labels", fetch = FetchType.EAGER)
    private Set<Exercise> exercises;
    @ManyToMany(mappedBy = "allowedSportLabels")
    private List<Ailment> labeledAilments;

    public void addLabeledExercise(Exercise exercise) {
        Hibernate.initialize(this.exercises.add(exercise));}
}

