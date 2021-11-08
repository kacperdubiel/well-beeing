package com.wellbeeing.wellbeeing.domain.diet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportProductDetail extends ProductAmountDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "report_id", referencedColumnName = "id")
    @JsonIgnore
    private Report report;
    @Column
    private LocalDateTime consumingTime;

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
