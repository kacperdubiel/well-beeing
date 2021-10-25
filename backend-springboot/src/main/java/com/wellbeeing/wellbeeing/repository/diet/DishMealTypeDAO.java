package com.wellbeeing.wellbeeing.repository.diet;

import com.wellbeeing.wellbeeing.domain.diet.DishMealType;
import com.wellbeeing.wellbeeing.domain.diet.type.EMealType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("dishMealTypeDAO")
public interface DishMealTypeDAO extends JpaRepository<DishMealType, UUID> {
    List<DishMealType> findAllByMealType(EMealType mealType);
}
