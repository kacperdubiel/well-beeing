package com.wellbeeing.wellbeeing.repository.diet;

import com.wellbeeing.wellbeeing.domain.diet.ReportDishDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("reportDishDetailDAO")
public interface ReportDishDetailDAO extends JpaRepository<ReportDishDetail, UUID> {
}
