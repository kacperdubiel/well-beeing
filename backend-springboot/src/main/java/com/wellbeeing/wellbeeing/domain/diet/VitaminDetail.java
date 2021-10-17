package com.wellbeeing.wellbeeing.domain.diet;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class VitaminDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Enumerated(EnumType.STRING)
    @Column
    private EVitamin vitaminType;
    @Column
    private double amount;
    @Enumerated(EnumType.STRING)
    @Column
    private EWeightMeasure measureType;
}
