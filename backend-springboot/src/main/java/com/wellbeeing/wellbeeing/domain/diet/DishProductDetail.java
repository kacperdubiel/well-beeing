package com.wellbeeing.wellbeeing.domain.diet;

import com.wellbeeing.wellbeeing.domain.diet.type.EWeightMeasure;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    @Column
    private double amount;
    @Enumerated(EnumType.STRING)
    @Column
    private EWeightMeasure measureType;


}
