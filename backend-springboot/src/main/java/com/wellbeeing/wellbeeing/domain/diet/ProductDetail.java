package com.wellbeeing.wellbeeing.domain.diet;

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
public abstract class ProductDetail {
    @Column
    private double amount;
    @Enumerated(EnumType.STRING)
    @Column
    private EWeightMeasure measureType;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
