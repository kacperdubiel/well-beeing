package com.wellbeeing.wellbeeing.domain.diet;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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
            name = "diet_nutrition_label",
            joinColumns = @JoinColumn(name = "diet_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "nutrition_label_id", referencedColumnName = "id")
    )
    private List<NutritionLabel> allowedNutritionLabels = new ArrayList<>();
    @Column
    private boolean connectedWithMacros;
    @Column
    private int suggestedCarbohydratesForDiet;
    @Column
    private int suggestedProteinsForDiet;
    @Column
    private int suggestedFatsForDiet;
}
