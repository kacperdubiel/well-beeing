package com.wellbeeing.wellbeeing.repository.diet;

import com.wellbeeing.wellbeeing.domain.diet.NutritionLabel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("nutritionLabelDAO")
public interface NutritionLabelDAO extends JpaRepository<NutritionLabel, UUID> {
}
