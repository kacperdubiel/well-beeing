package com.wellbeeing.wellbeeing.domain.diet;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class NutritionLabel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private String description;
    @Column(unique = true)
    private String name;
}
