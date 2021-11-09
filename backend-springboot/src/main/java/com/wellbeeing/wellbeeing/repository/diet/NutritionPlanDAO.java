package com.wellbeeing.wellbeeing.repository.diet;

import com.wellbeeing.wellbeeing.domain.diet.NutritionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("nutritionPlanDAO")
public interface NutritionPlanDAO extends JpaRepository<NutritionPlan, UUID> {
}
