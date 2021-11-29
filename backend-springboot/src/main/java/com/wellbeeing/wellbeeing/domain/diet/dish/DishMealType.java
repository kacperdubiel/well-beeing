package com.wellbeeing.wellbeeing.domain.diet.dish;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.diet.type.EMealType;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishMealType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "dish_id")
    @JsonIgnore
    private Dish dish;
    @Enumerated(EnumType.STRING)
    @Column
    private EMealType mealType;
}
