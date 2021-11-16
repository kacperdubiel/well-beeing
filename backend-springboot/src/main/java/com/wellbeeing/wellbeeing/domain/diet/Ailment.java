package com.wellbeeing.wellbeeing.domain.diet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.diet.type.EAilmentType;
import com.wellbeeing.wellbeeing.domain.sport.SportLabelAilment;
import com.wellbeeing.wellbeeing.domain.sport.TrainingPosition;
import lombok.*;
import com.wellbeeing.wellbeeing.domain.SportLabel;
import javax.persistence.*;
import java.util.*;

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
    @ManyToMany
    @JoinTable(
            name = "ailment_nutrition_label",
            joinColumns = @JoinColumn(name = "ailment_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "nutrition_label_id", referencedColumnName = "id")
    )
    private List<NutritionLabel> allowedNutritionLabels = new ArrayList<>();
//    @ManyToMany
//    @JoinTable(
//            name = "ailment_sportLabel",
//            joinColumns = @JoinColumn(name = "ailment_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "sport_label_id", referencedColumnName = "sportLabel_Id")
//    )
//    private List<SportLabel> allowedSportLabels;
    @JsonIgnore
    @OneToMany(mappedBy = "ailment", cascade = CascadeType.ALL)
    private Set<SportLabelAilment> sportLabels = new HashSet<>();
    @Column
    private int changeInCalories;
    @Column
    private int changeInProteinsGramPerKilogram;
    @Column
    private boolean isLowGlycemicIndexRecommended;
}


