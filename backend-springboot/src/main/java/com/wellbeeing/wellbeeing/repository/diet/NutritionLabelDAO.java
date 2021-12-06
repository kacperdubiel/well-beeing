package com.wellbeeing.wellbeeing.repository.diet;

import com.wellbeeing.wellbeeing.domain.diet.NutritionLabel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("nutritionLabelDAO")
public interface NutritionLabelDAO extends JpaRepository<NutritionLabel, UUID> {
    List<NutritionLabel> findAllByActive(boolean active);
    Page<NutritionLabel> findAllByCreatorIdAndActive(UUID creatorId, boolean active, Pageable pageable);
    List<NutritionLabel> findAllByNameAndActive(String name, boolean active);
    @Modifying
    @Query("delete from NutritionLabel d where d.nutritionLabelId=:uuid")
    void deleteById(@Param("uuid") UUID uuid);
}
