package com.wellbeeing.wellbeeing.domain.diet;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class ProfileDietCalculation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private double bmi;
    @Column(nullable = false)
    private EBMIResult bmiResultType;
    @Column(nullable = false)
    private int suggestedCalories;
    @Column
    private int suggestedCarbohydrates;
    @Column
    private int suggestedFats;
    @Column
    private int suggestedProteins;
    @Column
    private int suggestedBreakfastCalories;
    @Column
    private int suggestedLunchCalories;
    @Column
    private int suggestedDinnerCalories;
    @Column
    private int suggestedSnackCalories;
    @Column
    private int suggestedBreakfastGlycemic;
    @Column
    private int suggestedLunchGlycemic;
    @Column
    private int suggestedDinnerGlycemic;
    @Column
    private int suggestedSnackGlycemic;
}
