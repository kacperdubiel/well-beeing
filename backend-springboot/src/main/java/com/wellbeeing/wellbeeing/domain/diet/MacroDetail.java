package com.wellbeeing.wellbeeing.domain.diet;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class MacroDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Enumerated(EnumType.STRING)
    @Column
    private EDetailedMacro detailedMacroType;
    @Column
    private double amount;
    @Enumerated(EnumType.STRING)
    @Column
    private EWeightMeasure measureType;
}
