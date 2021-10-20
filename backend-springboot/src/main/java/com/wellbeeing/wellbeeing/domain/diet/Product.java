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

    private double caloriesPerGramInGram;
    @Column
    private double fatsPerGramInGrams;
    @Column
    private double carbohydratesPerGramInGram;
    @Column
    private double proteinsPerGramInGram;
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<MacroDetail> macroDetails;
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<MineralDetail> mineralDetails;
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<VitaminDetail> vitaminDetails;








}
