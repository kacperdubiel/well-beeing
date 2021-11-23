package com.wellbeeing.wellbeeing.domain.diet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NutritionalValueDerivedData {
    private double derivedCalories;

    private double derivedFiber;
    private double derivedSalt;
    private double derivedCholesterol;
    private double derivedCaffeine;

    private double derivedCarbohydrates;
    private double derivedProteins;
    private double derivedFats;

    private double derivedSugar;
    private double derivedSaturatedFats;
}
