package com.wellbeeing.wellbeeing.repository.sport;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.sport.SportReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface SportReportDAO extends JpaRepository<SportReport, UUID>, JpaSpecificationExecutor<SportReport> {
    Optional<SportReport> findByReportDate(LocalDate reportDate);
    Optional<SportReport> findByReportDateAndReportOwner(LocalDate reportDate, Profile reportOwner);
}
