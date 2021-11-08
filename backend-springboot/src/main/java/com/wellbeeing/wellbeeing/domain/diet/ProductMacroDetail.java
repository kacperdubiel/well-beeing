package com.wellbeeing.wellbeeing.domain.diet;

import com.wellbeeing.wellbeeing.domain.diet.type.EDetailedMacro;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProductMacroDetail extends ProductElementDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Enumerated(EnumType.STRING)
    @Column
    private EDetailedMacro detailedMacroType;
}
