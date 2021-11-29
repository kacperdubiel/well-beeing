package com.wellbeeing.wellbeeing.domain.diet.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public abstract class ProductElementDetail {
    @Column
    private double amountOfElementPerHundredGrams;
    @Enumerated(EnumType.STRING)
    @Column
    private EWeightMeasure measureType;
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product elementsProduct;

    public double countAmountInGramsPerGram(){
        return this.amountOfElementPerHundredGrams * this.measureType.getNumberOfGrams() / 100;
    }
}
