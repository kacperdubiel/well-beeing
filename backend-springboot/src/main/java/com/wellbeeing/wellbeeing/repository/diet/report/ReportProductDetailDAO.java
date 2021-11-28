package com.wellbeeing.wellbeeing.repository.diet.report;

import com.wellbeeing.wellbeeing.domain.diet.report.ReportProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("reportProductDetailDAO")
public interface ReportProductDetailDAO extends JpaRepository<ReportProductDetail, UUID> {
    @Modifying
    @Query("delete from ReportProductDetail d where d.id=:uuid")
    void deleteById(@Param("uuid") UUID uuid);
}
