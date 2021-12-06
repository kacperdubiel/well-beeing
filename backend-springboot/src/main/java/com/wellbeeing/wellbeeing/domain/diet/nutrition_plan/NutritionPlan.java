package com.wellbeeing.wellbeeing.domain.diet.nutrition_plan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.diet.Diet;
import com.wellbeeing.wellbeeing.domain.social.Post;
import lombok.*;
import javax.persistence.*;
import java.util.*;

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

    @JsonIgnore
    @OneToMany(mappedBy = "originalNutritionPlan", cascade = CascadeType.ALL)
    private Set<Post> sharingPosts = new HashSet<>();
}
