package com.wellbeeing.wellbeeing.repository.diet;

import com.wellbeeing.wellbeeing.domain.diet.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Repository("reportDAO")
public interface ReportDAO extends JpaRepository<Report, UUID> {
    Optional<Report> findByReportDate(Date reportDate);
}
