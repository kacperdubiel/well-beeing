package com.wellbeeing.wellbeeing.domain.diet.product;

import com.wellbeeing.wellbeeing.domain.diet.type.EVitamin;
import com.wellbeeing.wellbeeing.domain.diet.type.EWeightMeasure;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductVitaminDetail extends ProductElementDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Enumerated(EnumType.STRING)
    @Column
    private EVitamin vitaminType;

    @Builder
    public ProductVitaminDetail(EVitamin vitaminType, double amountOfElementPerHundredGrams,
                                EWeightMeasure measureType, Product elementsProduct) {
        super(amountOfElementPerHundredGrams, measureType, elementsProduct);
        this.vitaminType = vitaminType;
    }
}
