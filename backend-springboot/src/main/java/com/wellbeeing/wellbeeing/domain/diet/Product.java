package com.wellbeeing.wellbeeing.domain.diet;
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
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<MacroDetail> macroDetails = new ArrayList<>();
    @OneToMany(mappedBy = "product")
    private List<MineralDetail> mineralDetails =  new ArrayList<>();
    @OneToMany(mappedBy = "product")
    private List<VitaminDetail> vitaminDetails = new ArrayList<>();
}
