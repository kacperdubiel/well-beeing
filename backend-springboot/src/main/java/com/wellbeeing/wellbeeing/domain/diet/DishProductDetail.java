package com.wellbeeing.wellbeeing.domain.diet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishProductDetail extends ProductAmountDetail implements NutritionalValueDerivable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "dish_id")
    @JsonIgnore
    private Dish dish;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "derivedCalories", column = @Column(name = "derived_calories")),
            @AttributeOverride( name = "derivedFats", column = @Column(name = "derived_fats")),
            @AttributeOverride( name = "derivedProteins", column = @Column(name = "derived_proteins")),
            @AttributeOverride( name = "derivedCarbohydrates", column = @Column(name = "derived_carbohydrates"))
    })
    NutritionalValueDerivedData derivedNutritionalValues;

    @Override
    public void setDerived(){
        this.derivedNutritionalValues.setDerivedCalories(countCalories());
        this.derivedNutritionalValues.setDerivedCarbohydrates(countCarbohydrates());
        this.derivedNutritionalValues.setDerivedFats(countFats());
        this.derivedNutritionalValues.setDerivedProteins(countProteins());
    }
}
