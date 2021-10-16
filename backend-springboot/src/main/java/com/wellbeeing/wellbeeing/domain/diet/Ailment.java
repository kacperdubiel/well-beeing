package com.wellbeeing.wellbeeing.domain.diet;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
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
    @Column
    private int changeInProteinsGramPerKilogram;
    @Column
    private boolean isLowGlycemicIndexRecommended;
}


