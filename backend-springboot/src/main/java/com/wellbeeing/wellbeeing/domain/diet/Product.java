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
    private double carbohydratesPerHundredGrams;
    @Column
    private double proteinsPerHundredGrams;
    @OneToMany(mappedBy = "elementsProduct", cascade = CascadeType.ALL)
    private List<ProductMacroDetail> macroDetails = new ArrayList<>();
    @OneToMany(mappedBy = "elementsProduct")
    private List<ProductMineralDetail> mineralDetails =  new ArrayList<>();
    @OneToMany(mappedBy = "elementsProduct")
    private List<ProductVitaminDetail> vitaminDetails = new ArrayList<>();
    @Column
    @Enumerated(EnumType.STRING)
    private EGlycemicIndexLevel glycemicIndexLevel;
}
