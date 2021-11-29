package com.wellbeeing.wellbeeing.domain.diet.product;

import com.wellbeeing.wellbeeing.domain.diet.type.EMineral;
import com.wellbeeing.wellbeeing.domain.diet.type.EWeightMeasure;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductMineralDetail extends ProductElementDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Enumerated(EnumType.STRING)
    @Column
    private EMineral mineralType;

    @Builder
    public ProductMineralDetail(EMineral mineralType, double amountOfElementPerHundredGrams,
                                EWeightMeasure measureType, Product elementsProduct) {
        super(amountOfElementPerHundredGrams, measureType, elementsProduct);
        this.mineralType = mineralType;
    }
}
