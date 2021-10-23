package com.wellbeeing.wellbeeing.domain.diet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.diet.type.EDetailedMacro;
import com.wellbeeing.wellbeeing.domain.diet.type.EWeightMeasure;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class MacroDetail extends ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Enumerated(EnumType.STRING)
    @Column
    private EDetailedMacro detailedMacroType;
    /*@Column
    private double amount;
    @Enumerated(EnumType.STRING)
    @Column
    private EWeightMeasure measureType;*/
    /*@ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;*/
}
