package com.wellbeeing.wellbeeing.repository.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.telemedic.Measure;
import com.wellbeeing.wellbeeing.domain.telemedic.MeasureType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository("measureDAO")
public interface MeasureDAO extends JpaRepository<Measure, UUID> {
    Page<Measure> findByOwnerAndMeasureType(Profile owner, MeasureType measureType, Pageable pageable);
    List<Measure> findByOwnerAndMeasureDateBetweenOrderByMeasureDate(Profile owner, LocalDateTime start, LocalDateTime end);
}
