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

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "derivedCalories", column = @Column(name = "derived_calories")),
            @AttributeOverride( name = "derivedFats", column = @Column(name = "derived_fats")),
            @AttributeOverride( name = "derivedProteins", column = @Column(name = "derived_proteins")),
            @AttributeOverride( name = "derivedCarbohydrates", column = @Column(name = "derived_carbohydrates"))
    })
    NutritionalValueDerivedData derivedNutritionalValues;

    public double countFats(){
        return this.getDish().getDerivedNutritionalValues().getDerivedFats() * this.getPortions();
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
    public void setDerived(){
        this.derivedNutritionalValues.setDerivedCalories(countCalories());
        this.derivedNutritionalValues.setDerivedCarbohydrates(countCarbohydrates());
        this.derivedNutritionalValues.setDerivedFats(countFats());
        this.derivedNutritionalValues.setDerivedProteins(countProteins());
    }
}
