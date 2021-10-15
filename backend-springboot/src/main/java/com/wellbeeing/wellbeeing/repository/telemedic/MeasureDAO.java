package com.wellbeeing.wellbeeing.repository.telemedic;

import com.wellbeeing.wellbeeing.domain.telemedic.Measure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("measureDAO")
public interface MeasureDAO extends JpaRepository<Measure, UUID> {

}
