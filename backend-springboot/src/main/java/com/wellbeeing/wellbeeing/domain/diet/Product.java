package com.wellbeeing.wellbeeing.domain.diet;
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
    private double fatsInGrams;
    @Column
    private double carbohydratesInGrams;
    @Column
    private double proteinsInGrams;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "macro_details_id", referencedColumnName = "id")
    private List<MacroDetail> macroDetails;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "mineral_details_id", referencedColumnName = "id")
    private List<MacroDetail> mineralDetails;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "mineral_details_id", referencedColumnName = "id")
    private List<MacroDetail> vitaminDetails;








}
