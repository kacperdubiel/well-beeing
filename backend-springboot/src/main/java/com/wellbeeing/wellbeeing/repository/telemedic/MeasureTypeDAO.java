package com.wellbeeing.wellbeeing.repository.telemedic;

import com.wellbeeing.wellbeeing.domain.telemedic.MeasureType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("measureTypeDAO")
public interface MeasureTypeDAO extends JpaRepository<MeasureType, UUID> {

}