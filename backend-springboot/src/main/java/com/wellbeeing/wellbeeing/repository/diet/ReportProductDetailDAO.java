package com.wellbeeing.wellbeeing.repository.diet;

import com.wellbeeing.wellbeeing.domain.diet.ReportProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("reportProductDetailDAO")
public interface ReportProductDetailDAO extends JpaRepository<ReportProductDetail, UUID> {
}
