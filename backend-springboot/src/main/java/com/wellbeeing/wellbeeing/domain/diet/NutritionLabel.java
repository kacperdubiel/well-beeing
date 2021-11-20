package com.wellbeeing.wellbeeing.domain.diet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.account.DieticianProfile;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NutritionLabel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "nutritionLabel_Id")
    private UUID nutritionLabelId;
    @Column
    private String description;
    @Column
    private String name;
    @Column
    boolean active = true;
    @JsonIgnore
    @ManyToMany(mappedBy = "allowedForNutritionLabels" )
    List<Dish> labeledDishes = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "ailment_nutritionLabel",
            joinColumns = @JoinColumn(name = "nutrition_label_id", referencedColumnName = "nutritionLabel_Id"),
            inverseJoinColumns = @JoinColumn(name = "ailment_id", referencedColumnName = "id")
    )
    private List<Ailment> labeledAilments;
    @ManyToMany
    @JoinTable(
            name = "diet_nutrition_label",
            joinColumns = @JoinColumn(name = "nutrition_label_id", referencedColumnName = "nutritionLabel_Id"),
            inverseJoinColumns = @JoinColumn(name = "diet_id", referencedColumnName = "id")
    )
    private List<Diet> labeledDiets;
    @ManyToOne
    private DieticianProfile creator;
}
