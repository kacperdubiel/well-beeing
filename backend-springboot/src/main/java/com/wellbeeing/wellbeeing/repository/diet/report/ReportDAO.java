package com.wellbeeing.wellbeeing.repository.diet.report;

import com.wellbeeing.wellbeeing.domain.diet.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository("reportDAO")
public interface ReportDAO extends JpaRepository<Report, UUID> {
   Optional<Report> findByReportDateAndReportOwnerId(LocalDate reportDate, UUID reportOwnerId);
}
