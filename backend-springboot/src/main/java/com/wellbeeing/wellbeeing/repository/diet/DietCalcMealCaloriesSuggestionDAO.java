package com.wellbeeing.wellbeeing.repository.diet;

import com.wellbeeing.wellbeeing.domain.diet.calculation.DietCalcMealCaloriesSuggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("dietCalcMealCaloriesSuggestionDAO")
public interface DietCalcMealCaloriesSuggestionDAO extends JpaRepository<DietCalcMealCaloriesSuggestion, UUID> {
}
