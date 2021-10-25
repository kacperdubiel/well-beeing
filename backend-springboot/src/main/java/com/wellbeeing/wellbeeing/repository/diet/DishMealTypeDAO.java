package com.wellbeeing.wellbeeing.repository.diet;

import com.wellbeeing.wellbeeing.domain.diet.DishMealType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("dishMealTypeDAO")
public interface DishMealTypeDAO extends JpaRepository<DishMealType, UUID> {

}
