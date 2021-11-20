package com.wellbeeing.wellbeeing.domain.diet;

import com.wellbeeing.wellbeeing.domain.diet.type.EDetailedMacro;
import com.wellbeeing.wellbeeing.domain.diet.type.EWeightMeasure;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductMacroDetail extends ProductElementDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Enumerated(EnumType.STRING)
    @Column
    private EDetailedMacro detailedMacroType;

    @Builder
    public ProductMacroDetail(EDetailedMacro macroType, double amountOfElementPerHundredGrams,
                                EWeightMeasure measureType, Product elementsProduct) {
        super(amountOfElementPerHundredGrams, measureType, elementsProduct);
        this.detailedMacroType = macroType;
    }
}
