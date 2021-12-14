package com.wellbeeing.wellbeeing.domain.diet.report;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.diet.NutritionalValueDerivedData;
import com.wellbeeing.wellbeeing.domain.diet.ProductAmountDetail;
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

    /*@Embedded
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
    })*/
    @Transient
    NutritionalValueDerivedData derivedNutritionalValues = new NutritionalValueDerivedData();

    @Override
    @PostLoad
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
