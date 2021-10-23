package com.wellbeeing.wellbeeing.domain.diet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
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
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<MacroDetail> macroDetails;
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<MineralDetail> mineralDetails;
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<VitaminDetail> vitaminDetails;








}
