package com.wellbeeing.wellbeeing.domain.diet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.SportLabel;
import com.wellbeeing.wellbeeing.domain.diet.type.EAilmentType;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ailment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private String name;
    @Column
    private String description;
    @Enumerated(EnumType.STRING)
    @Column
    private EAilmentType type;
    @ManyToMany(mappedBy = "labeledAilments")
    @JsonIgnore
    private List<NutritionLabel> allowedNutritionLabels;
    @ManyToMany
    @JoinTable(
            name = "ailment_sportLabel",
            joinColumns = @JoinColumn(name = "ailment_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "sport_label_id", referencedColumnName = "sportLabel_Id")
    )
    private List<SportLabel> allowedSportLabels;
    @Column
    private double changeInCalories;
    @Column
    private double changeInProteinsGramPerKilogram;
    @Column
    private boolean isLowGlycemicIndexRecommended;
}


