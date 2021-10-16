package com.wellbeeing.wellbeeing.domain.diet;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
public class NutritionLabel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private String description;
    @Column(unique = true)
    private String name;
}
