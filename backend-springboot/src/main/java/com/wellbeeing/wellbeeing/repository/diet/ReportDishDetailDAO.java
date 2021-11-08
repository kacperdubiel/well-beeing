package com.wellbeeing.wellbeeing.repository.diet;

import com.wellbeeing.wellbeeing.domain.diet.ReportDishDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("reportDishDetailDAO")
public interface ReportDishDetailDAO extends JpaRepository<ReportDishDetail, UUID> {
    @Modifying
    @Query("delete from ReportDishDetail d where d.id=:uuid")
    void deleteById(@Param("uuid") UUID uuid);
}
