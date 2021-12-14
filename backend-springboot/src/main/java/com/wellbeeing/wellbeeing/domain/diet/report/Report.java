package com.wellbeeing.wellbeeing.domain.diet.report;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.diet.NutritionalValueDerivable;
import com.wellbeeing.wellbeeing.domain.diet.NutritionalValueDerivedData;
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

   /* @Embedded
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
    public double countFiber() {
        double result = 0;
        for(ReportProductDetail rpd : this.productDetailsList){
            result += rpd.getDerivedNutritionalValues().getDerivedFiber();
        }
        for(ReportDishDetail rdd : this.dishDetailsList){
            result += rdd.getDerivedNutritionalValues().getDerivedFiber();
        }
        return result;
    }

    @Override
    public double countSalt() {
        double result = 0;
        for(ReportProductDetail rpd : this.productDetailsList){
            result += rpd.getDerivedNutritionalValues().getDerivedSalt();
        }
        for(ReportDishDetail rdd : this.dishDetailsList){
            result += rdd.getDerivedNutritionalValues().getDerivedSalt();
        }
        return result;
    }

    @Override
    public double countCholesterol() {
        double result = 0;
        for(ReportProductDetail rpd : this.productDetailsList){
            result += rpd.getDerivedNutritionalValues().getDerivedCholesterol();
        }
        for(ReportDishDetail rdd : this.dishDetailsList){
            result += rdd.getDerivedNutritionalValues().getDerivedCholesterol();
        }
        return result;
    }

    @Override
    public double countCaffeine() {
        double result = 0;
        for(ReportProductDetail rpd : this.productDetailsList){
            result += rpd.getDerivedNutritionalValues().getDerivedCaffeine();
        }
        for(ReportDishDetail rdd : this.dishDetailsList){
            result += rdd.getDerivedNutritionalValues().getDerivedCaffeine();
        }
        return result;
    }

    @Override
    public double countSugar() {
        double result = 0;
        for(ReportProductDetail rpd : this.productDetailsList){
            result += rpd.getDerivedNutritionalValues().getDerivedSugar();
        }
        for(ReportDishDetail rdd : this.dishDetailsList){
            result += rdd.getDerivedNutritionalValues().getDerivedSugar();
        }
        return result;
    }

    @Override
    public double countSaturatedFats() {
        double result = 0;
        for(ReportProductDetail rpd : this.productDetailsList){
            result += rpd.getDerivedNutritionalValues().getDerivedSaturatedFats();
        }
        for(ReportDishDetail rdd : this.dishDetailsList){
            result += rdd.getDerivedNutritionalValues().getDerivedSaturatedFats();
        }
        return result;
    }

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
