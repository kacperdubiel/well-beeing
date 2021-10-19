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
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private String name;
    @Column
    private String description;
    @Column(nullable = false)
    private boolean connectedWithMacros;
    @Column
    private int suggestedCarbohydratesForDiet;
    @Column
    private int suggestedProteinsForDiet;
    @Column()
    private int suggestedFatsForDiet;
}
