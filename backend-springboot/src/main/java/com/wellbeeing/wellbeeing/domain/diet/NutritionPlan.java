package com.wellbeeing.wellbeeing.domain.diet;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NutritionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private Date generationDate;
    @Column
    private String name;
    @Column
    private boolean main;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Profile creatorProfile;
    @ManyToOne
    @JoinColumn(name = "suggested_id")
    private Profile ownerProfile;
    @OneToMany(mappedBy = "nutritionPlan", cascade = CascadeType.REMOVE)
    private List<NutritionPlanPosition> nutritionPlanPositions = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "diet_id")
    private Diet diet;
}
