package com.wellbeeing.wellbeeing.domain.diet;

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
    @OneToMany(mappedBy = "dish")
    List<DishProductDetail> dishProductDetails;
    @OneToMany(mappedBy = "dish")
    List<DishMealType> dishMealTypes;
    @Column
    double derivedCalories;
    @Column
    double derivedCarbohydrates;
    @Column
    double derivedProteins;
    @Column
    double derivedFats;


}
