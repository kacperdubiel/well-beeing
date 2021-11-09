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
    private double derivedCarbohydrates;
    private double derivedProteins;
    private double derivedFats;
}
