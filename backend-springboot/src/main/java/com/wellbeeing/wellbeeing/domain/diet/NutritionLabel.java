package com.wellbeeing.wellbeeing.domain.diet;

import lombok.*;

import javax.persistence.*;
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
    private UUID id;
    @Column
    private String description;
    @Column(unique = true)
    private String name;
    @ManyToMany(mappedBy = "allowedForNutritionLabels" )
    List<Dish> labelledDishes;
}
