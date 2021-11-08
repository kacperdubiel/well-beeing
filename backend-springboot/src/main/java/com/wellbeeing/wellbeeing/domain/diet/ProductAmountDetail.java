package com.wellbeeing.wellbeeing.domain.diet;

import com.wellbeeing.wellbeeing.domain.diet.type.EWeightMeasure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class ProductAmountDetail implements NutritionalValueDerivable {
    @Column
    private double amountOfProduct;
    @Enumerated(EnumType.STRING)
    @Column
    private EWeightMeasure measureType;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Override
    public double countFats(){
        return this.getAmountOfProduct() * this.getMeasureType().getNumberOfGrams() *
                this.getProduct().getFatsPerHundredGrams()/100;
    }

    @Override
    public double countCarbohydrates(){
        return this.getAmountOfProduct() * this.getMeasureType().getNumberOfGrams() *
                this.getProduct().getCarbohydratesPerHundredGrams()/100;
    }

    @Override
    public double countProteins(){
        return this.getAmountOfProduct() * this.getMeasureType().getNumberOfGrams() *
                this.getProduct().getProteinsPerHundredGrams()/100;
    }

    @Override
    public double countCalories(){
        return this.getAmountOfProduct() * this.getMeasureType().getNumberOfGrams() *
                this.getProduct().getCaloriesPerHundredGrams()/100;
    }
}
