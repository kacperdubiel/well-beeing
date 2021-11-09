package com.wellbeeing.wellbeeing.domain.diet.calculation;

import com.wellbeeing.wellbeeing.domain.diet.type.EBMIResult;
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
public class ProfileDietCalculation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private double bmi;
    @Enumerated(EnumType.STRING)
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
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "dietCalculation")
    List<DietCalcMealSuggestions> dietCalcMealSuggestions
            ;
}
