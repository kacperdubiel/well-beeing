package com.wellbeeing.wellbeeing.repository.diet;

import com.wellbeeing.wellbeeing.domain.diet.DishMealType;
import com.wellbeeing.wellbeeing.domain.diet.type.EMealType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("dishMealTypeDAO")
public interface DishMealTypeDAO extends JpaRepository<DishMealType, UUID> {
    List<DishMealType> findAllByMealType(EMealType mealType);
    @Modifying
    @Query("delete from DishMealType d where d.id=:uuid")
    void deleteById(@Param("uuid") UUID uuid);
}
