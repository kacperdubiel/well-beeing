package com.wellbeeing.wellbeeing.domain.diet.calculation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.diet.type.EGlycemicIndexLevel;
import com.wellbeeing.wellbeeing.domain.diet.type.EMealType;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DietCalcMealGlycemicIndexSuggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diet_calculation_id")
    @JsonIgnore
    private ProfileDietCalculation dietCalculation;
    @Enumerated(EnumType.STRING)
    @Column
    private EMealType mealType;
    @Column
    private EGlycemicIndexLevel glycemicIndexLevel;
}
