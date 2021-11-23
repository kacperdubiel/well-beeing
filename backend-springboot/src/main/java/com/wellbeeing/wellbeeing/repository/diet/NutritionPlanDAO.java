package com.wellbeeing.wellbeeing.repository.diet;

import com.wellbeeing.wellbeeing.domain.diet.NutritionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("nutritionPlanDAO")
public interface NutritionPlanDAO extends JpaRepository<NutritionPlan, UUID> {
    List<NutritionPlan> findAllByCreatorProfileIdAndOwnerProfileId(UUID creatorProfileId, UUID ownerProfileId);
    List<NutritionPlan> findAllByCreatorProfileId(UUID creatorProfileId);
    List<NutritionPlan> findAllByOwnerProfileId(UUID creatorProfileId);
    @Modifying
    @Query("delete from NutritionPlan d where d.id=:uuid")
    void deleteById(@Param("uuid") UUID uuid);
}
