package com.wellbeeing.wellbeeing.domain.diet;

import com.wellbeeing.wellbeeing.domain.diet.type.EVitamin;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Builder
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
}
