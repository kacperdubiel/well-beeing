package com.wellbeeing.wellbeeing.repository.diet.calculation;

import com.wellbeeing.wellbeeing.domain.diet.calculation.DietCalcMealSuggestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("dietCalcMealSuggestionDAO")
public interface DietCalcMealSuggestionDAO extends JpaRepository<DietCalcMealSuggestions, UUID> {
}
