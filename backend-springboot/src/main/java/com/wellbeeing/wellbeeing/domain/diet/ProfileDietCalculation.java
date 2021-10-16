package com.wellbeeing.wellbeeing.domain.diet;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
public class ProfileDietCalculation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private double bmi;
    @Column
    private EBMIResult bmiResultType;
    @Column
    private double basicMetabolism;
    @Column
    private double suggestedCalories;
    @Column
    private double suggestedCarbohydrates;
    @Column
    private double suggestedFats;
    @Column
    private double suggestedProteins;
    @Column
    private double suggestedBreakfastCalories;
    @Column
    private double suggestedLunchCalories;
    @Column
    private double suggestedDinnerCalories;
    @Column
    private double suggestedSnackCalories;
    @Column
    private double suggestedSupperCalories;
    @Column
    private EGlycemicIndexLevel suggestedBreakfastGlycemic;
    @Column
    private EGlycemicIndexLevel suggestedLunchGlycemic;
    @Column
    private EGlycemicIndexLevel suggestedDinnerGlycemic;
    @Column
    private EGlycemicIndexLevel suggestedSnackGlycemic;
    @Column
    private EGlycemicIndexLevel suggestedSupperGlycemic;

}
