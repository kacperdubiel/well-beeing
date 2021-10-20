package com.wellbeeing.wellbeeing.domain.diet;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private String name;
    @Column
    private String description;
    @ManyToMany
    @JoinTable(
            name = "ailment_nutrition_label",
            joinColumns = @JoinColumn(name = "ailment_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "nutrition_label_id", referencedColumnName = "nutrition_label_id")
    )
    private List<NutritionLabel> allowedNutritionLabels;
    @Column
    private boolean connectedWithMacros;
    @Column
    private int suggestedCarbohydratesForDiet;
    @Column
    private int suggestedProteinsForDiet;
    @Column
    private int suggestedFatsForDiet;
}
