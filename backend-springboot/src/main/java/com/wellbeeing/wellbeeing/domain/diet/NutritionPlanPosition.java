package com.wellbeeing.wellbeeing.domain.diet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.diet.type.EMealType;
import com.wellbeeing.wellbeeing.domain.diet.type.EWeekDay;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NutritionPlanPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "dish_id", referencedColumnName = "id")
    private Dish dish;
    @Enumerated(EnumType.STRING)
    @Column
    private EMealType mealType;
    @Enumerated(EnumType.STRING)
    @Column
    private EWeekDay weekDay;
    @ManyToOne
    @JoinColumn(name = "nutrition_plan_id", referencedColumnName = "id")
    @JsonIgnore
    private NutritionPlan nutritionPlan;
}
