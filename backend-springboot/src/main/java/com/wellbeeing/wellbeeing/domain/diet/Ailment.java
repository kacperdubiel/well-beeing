package com.wellbeeing.wellbeeing.domain.diet;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Ailment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private String description;
    @Column
    private EAilmentType type;
    @JoinTable(
            name = "ailment_label",
            joinColumns = @JoinColumn(name = "label_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ailment_id", referencedColumnName = "id")
    )
    private List<NutritionLabel> allowedLabels;
    @Column
    private int changeInCalories;

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public EAilmentType getType() {
        return type;
    }

    public List<NutritionLabel> getAllowedLabels() {
        return allowedLabels;
    }

    public int getChangeInCalories() {
        return changeInCalories;
    }
}
