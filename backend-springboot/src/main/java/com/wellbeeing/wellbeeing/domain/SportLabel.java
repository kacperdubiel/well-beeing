package com.wellbeeing.wellbeeing.domain;

import com.wellbeeing.wellbeeing.domain.sport.Exercise;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class SportLabel {
    @Id
    private long sportlabel_id;

    @ManyToMany(mappedBy = "labels")
    private Set<Exercise> exercises;

    public SportLabel() {

    }
}
