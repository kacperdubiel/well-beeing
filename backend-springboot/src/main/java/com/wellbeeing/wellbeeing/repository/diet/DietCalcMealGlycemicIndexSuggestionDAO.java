package com.wellbeeing.wellbeeing.repository.diet;

import com.wellbeeing.wellbeeing.domain.diet.calculation.DietCalcMealGlycemicIndexSuggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("dietCalcMealGlycemicIndexSuggestionDAO")
public interface DietCalcMealGlycemicIndexSuggestionDAO extends JpaRepository<DietCalcMealGlycemicIndexSuggestion, UUID> {
}
