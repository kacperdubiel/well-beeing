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
            @AttributeOverride( name = "derivedCarbohydrates", column = @Column(name = "derived_carbohydrates")),
            @AttributeOverride( name = "derivedFiber", column = @Column(name = "derived_fiber")),
            @AttributeOverride( name = "derivedSalt", column = @Column(name = "derived_salt")),
            @AttributeOverride( name = "derivedCholesterol", column = @Column(name = "derived_cholesterol")),
            @AttributeOverride( name = "derivedCaffeine", column = @Column(name = "derived_caffeine")),
            @AttributeOverride( name = "derivedSugar", column = @Column(name = "derived_sugar")),
            @AttributeOverride( name = "derivedSaturatedFats", column = @Column(name = "derived_saturated_fats"))
    })
    NutritionalValueDerivedData derivedNutritionalValues;

    @Override
    public void setDerived(){
        this.derivedNutritionalValues.setDerivedCalories(countCalories());
        this.derivedNutritionalValues.setDerivedCarbohydrates(countCarbohydrates());
        this.derivedNutritionalValues.setDerivedFats(countFats());
        this.derivedNutritionalValues.setDerivedProteins(countProteins());
        this.derivedNutritionalValues.setDerivedFiber(countFiber());
        this.derivedNutritionalValues.setDerivedCholesterol(countCholesterol());
        this.derivedNutritionalValues.setDerivedSalt(countSalt());
        this.derivedNutritionalValues.setDerivedCaffeine(countCaffeine());
        this.derivedNutritionalValues.setDerivedSugar(countSugar());
        this.derivedNutritionalValues.setDerivedSaturatedFats(countSaturatedFats());
    }
}
