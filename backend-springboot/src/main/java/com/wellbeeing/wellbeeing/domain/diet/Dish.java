package com.wellbeeing.wellbeeing.domain.diet;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private String name;
    @Column
    private String imgDishPath;
    @Column
    private String description;
    @Column
    private String recipe;
    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
    List<DishProductDetail> dishProductDetails;
    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
    @JsonIgnore
    List<DishMealType> dishMealTypes;
    @ManyToMany
    @JoinTable(
            name = "dish_nutrition_label",
            joinColumns = @JoinColumn(name = "dish_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "nutrition_label_id", referencedColumnName = "id")
    )
    private List<NutritionLabel> allowedForNutritionLabels;
    @Column
    double derivedCalories;
    @Column
    double derivedCarbohydrates;
    @Column
    double derivedProteins;
    @Column
    double derivedFats;


}
