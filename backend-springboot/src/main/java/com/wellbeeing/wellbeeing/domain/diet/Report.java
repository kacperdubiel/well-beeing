package com.wellbeeing.wellbeeing.domain.diet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report implements NutritionalValueDerivable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "owner_profile_id")
    @JsonIgnore
    private Profile reportOwner;
    @Column
    private LocalDate reportDate;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    List<ReportProductDetail> productDetailsList = new ArrayList<>();
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    List<ReportDishDetail> dishDetailsList = new ArrayList<>();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "derivedCalories", column = @Column(name = "derived_calories")),
            @AttributeOverride( name = "derivedFats", column = @Column(name = "derived_fats")),
            @AttributeOverride( name = "derivedProteins", column = @Column(name = "derived_proteins")),
            @AttributeOverride( name = "derivedCarbohydrates", column = @Column(name = "derived_carbohydrates"))
    })
    NutritionalValueDerivedData derivedNutritionalValues;

    @Override
    public double countCalories() {
        double result = 0;
        for(ReportProductDetail rpd : this.productDetailsList){
            result += rpd.getDerivedNutritionalValues().getDerivedCalories();
        }
        for(ReportDishDetail rdd : this.dishDetailsList){
            result += rdd.getDerivedNutritionalValues().getDerivedCalories();
        }
        return result;
    }

    @Override
    public double countCarbohydrates() {
        double result = 0;
        for(ReportProductDetail rpd : this.productDetailsList){
            result += rpd.getDerivedNutritionalValues().getDerivedCarbohydrates();
        }
        for(ReportDishDetail rdd : this.dishDetailsList){
            result += rdd.getDerivedNutritionalValues().getDerivedCarbohydrates();
        }
        return result;
    }

    @Override
    public double countProteins() {
        double result = 0;
        for(ReportProductDetail rpd : this.productDetailsList){
            result += rpd.getDerivedNutritionalValues().getDerivedProteins();
        }
        for(ReportDishDetail rdd : this.dishDetailsList){
            result += rdd.getDerivedNutritionalValues().getDerivedProteins();
        }
        return result;
    }

    @Override
    public double countFats() {
        double result = 0;
        for(ReportProductDetail rpd : this.productDetailsList){
            result += rpd.getDerivedNutritionalValues().getDerivedFats();
        }
        for(ReportDishDetail rdd : this.dishDetailsList){
            result += rdd.getDerivedNutritionalValues().getDerivedFats();
        }
        return result;
    }

    @Override
    public void setDerived(){
        this.derivedNutritionalValues.setDerivedCalories(countCalories());
        this.derivedNutritionalValues.setDerivedCarbohydrates(countCarbohydrates());
        this.derivedNutritionalValues.setDerivedFats(countFats());
        this.derivedNutritionalValues.setDerivedProteins(countProteins());
    }
}
