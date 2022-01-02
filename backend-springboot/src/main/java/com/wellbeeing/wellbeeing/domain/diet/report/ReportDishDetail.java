package com.wellbeeing.wellbeeing.domain.diet.report;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.diet.dish.Dish;
import com.wellbeeing.wellbeeing.domain.diet.NutritionalValueDerivable;
import com.wellbeeing.wellbeeing.domain.diet.NutritionalValueDerivedData;
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
public class ReportDishDetail implements NutritionalValueDerivable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "dish_id", referencedColumnName = "id")
    private Dish dish;
    @ManyToOne
    @JoinColumn(name = "report_id", referencedColumnName = "id")
    @JsonIgnore
    private Report report;
    @Column
    private LocalDateTime consumingTime;
    @Column
    private double portions;

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

    public double countFats(){
        return this.getDish().getDerivedNutritionalValues().getDerivedFats() * this.getPortions();
    }

    @Override
    public double countFiber() {
        return this.getDish().getDerivedNutritionalValues().getDerivedFiber() * this.getPortions();
    }

    @Override
    public double countSalt() {
        return this.getDish().getDerivedNutritionalValues().getDerivedSalt() * this.getPortions();
    }

    @Override
    public double countCholesterol() {
        return this.getDish().getDerivedNutritionalValues().getDerivedCholesterol() * this.getPortions();
    }

    @Override
    public double countCaffeine() {
        return this.getDish().getDerivedNutritionalValues().getDerivedCaffeine() * this.getPortions();
    }

    @Override
    public double countSugar() {
        return this.getDish().getDerivedNutritionalValues().getDerivedSugar() * this.getPortions();
    }

    @Override
    public double countSaturatedFats() {
        return this.getDish().getDerivedNutritionalValues().getDerivedSaturatedFats() * this.getPortions();
    }

    public double countCarbohydrates(){
        return this.getDish().getDerivedNutritionalValues().getDerivedCarbohydrates() * this.getPortions();
    }

    public double countProteins(){
        return this.getDish().getDerivedNutritionalValues().getDerivedProteins() * this.getPortions();
    }

    public double countCalories(){
        return this.getDish().getDerivedNutritionalValues().getDerivedCalories() * this.getPortions();
    }

    @Override
    @PostLoad
    public void setDerived(){
        this.dish.setDerived();
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
