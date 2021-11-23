package com.wellbeeing.wellbeeing.domain.account;

import com.wellbeeing.wellbeeing.domain.diet.NutritionPlan;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class DieticianProfile extends SpecialistProfile {
    @Column
    @OneToMany(mappedBy = "id")
    private List<NutritionPlan> createdNutritionPlans = new ArrayList<>();
}
