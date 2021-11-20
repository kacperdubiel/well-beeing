package com.wellbeeing.wellbeeing.domain.diet;
import com.wellbeeing.wellbeeing.domain.diet.type.EGlycemicIndexLevel;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private String name;
    @Column
    private double caloriesPerHundredGrams;
    @Column
    private double fatsPerHundredGrams;
    @Column
    private double saturatedFatsPerHundredGrams;
    @Column
    private double carbohydratesPerHundredGrams;
    @Column
    private double sugarsPerHundredGrams;
    @Column
    private double proteinsPerHundredGrams;
    @Column
    private double fiberPerHundredGrams;
    @Column
    private double cholesterolPerHundredGrams;
    @Column
    private double saltPerHundredGrams;
    @Column
    private double caffeinePerHundredGrams;
    @OneToMany(mappedBy = "elementsProduct", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ProductMacroDetail> macroDetails = new ArrayList<>();
    @OneToMany(mappedBy = "elementsProduct", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ProductMineralDetail> mineralDetails =  new ArrayList<>();
    @OneToMany(mappedBy = "elementsProduct", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ProductVitaminDetail> vitaminDetails = new ArrayList<>();
    @Column
    @Enumerated(EnumType.STRING)
    private EGlycemicIndexLevel glycemicIndexLevel;
}
