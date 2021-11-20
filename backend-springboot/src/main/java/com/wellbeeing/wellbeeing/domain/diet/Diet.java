package com.wellbeeing.wellbeeing.domain.diet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
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
    @ManyToMany(mappedBy = "labeledDiets")
    @JsonIgnore
    private List<NutritionLabel> allowedNutritionLabels = new ArrayList<>();
    @Column
    private boolean connectedWithMacros;
}
