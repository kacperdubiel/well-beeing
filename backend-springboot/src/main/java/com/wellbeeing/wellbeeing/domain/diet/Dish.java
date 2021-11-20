package com.wellbeeing.wellbeeing.domain.diet;

import com.wellbeeing.wellbeeing.domain.account.DieticianProfile;
import com.wellbeeing.wellbeeing.domain.diet.type.EGlycemicIndexLevel;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dish implements NutritionalValueDerivable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(unique = true)
    private String name;
    @Column
    private String imgDishPath;
    @Column
    private String description;
    @Column(length = 1000)
    private String recipe;
    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
    List<DishProductDetail> dishProductDetails = new ArrayList<>();
    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
    List<DishMealType> dishMealTypes = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private DieticianProfile dishCreator;
    @Column
    private LocalDate createdDate;
    @ManyToMany
    @JoinTable(
            name = "dish_nutrition_label",
            joinColumns = @JoinColumn(name = "dish_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "nutrition_label_id", referencedColumnName = "nutritionLabel_Id")
    )
    private List<NutritionLabel> allowedForNutritionLabels = new ArrayList<>();
    @Embedded
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
    })
    NutritionalValueDerivedData derivedNutritionalValues;
    @Column
    private boolean active = true;
    @Column
    private boolean draft = true;
    @Column
    @Enumerated(EnumType.STRING)
    private EGlycemicIndexLevel glycemicIndexLevel;

    @Override
    public double countCalories() {
        return this.getDishProductDetails()
                .stream()
                .mapToDouble(ProductAmountDetail::countCalories)
                .sum();
    }

    @Override
    public double countCarbohydrates() {
        return this.getDishProductDetails()
                .stream()
                .mapToDouble(ProductAmountDetail::countCarbohydrates)
                .sum();
    }

    @Override
    public double countProteins() {
        return this.getDishProductDetails()
                .stream()
                .mapToDouble(ProductAmountDetail::countProteins)
                .sum();
    }

    @Override
    public double countFats() {
        return this.getDishProductDetails()
                .stream()
                .mapToDouble(ProductAmountDetail::countFats)
                .sum();
    }

    @Override
    public double countFiber() {
        return this.getDishProductDetails()
                .stream()
                .mapToDouble(ProductAmountDetail::countFiber)
                .sum();
    }

    @Override
    public double countSalt() {
        return this.getDishProductDetails()
                .stream()
                .mapToDouble(ProductAmountDetail::countSalt)
                .sum();
    }

    @Override
    public double countCholesterol() {
        return this.getDishProductDetails()
                .stream()
                .mapToDouble(ProductAmountDetail::countCholesterol)
                .sum();
    }

    @Override
    public double countCaffeine() {
        return this.getDishProductDetails()
                .stream()
                .mapToDouble(ProductAmountDetail::countCaffeine)
                .sum();
    }

    @Override
    public double countSugar() {
        return this.getDishProductDetails()
                .stream()
                .mapToDouble(ProductAmountDetail::countSugar)
                .sum();
    }

    @Override
    public double countSaturatedFats() {
        return this.getDishProductDetails()
                .stream()
                .mapToDouble(ProductAmountDetail::countSaturatedFats)
                .sum();
    }

    @Override
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
