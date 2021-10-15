package com.wellbeeing.wellbeeing.domain.diet;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private boolean connectedWithMacros;
    @Column
    private int suggestedCarbohydratesForDiet;
    @Column
    private int suggestedProteinsForDiet;
    @Column()
    private int suggestedFatsForDiet;
}
