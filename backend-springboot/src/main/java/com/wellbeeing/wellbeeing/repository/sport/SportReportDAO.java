package com.wellbeeing.wellbeeing.repository.sport;

import com.wellbeeing.wellbeeing.domain.diet.Report;
import com.wellbeeing.wellbeeing.domain.sport.SportReport;
import com.wellbeeing.wellbeeing.domain.sport.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface SportReportDAO extends JpaRepository<SportReport, UUID>, JpaSpecificationExecutor<SportReport> {
    Optional<SportReport> findByReportDate(LocalDate reportDate);
}
