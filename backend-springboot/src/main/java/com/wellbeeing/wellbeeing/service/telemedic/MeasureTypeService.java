package com.wellbeeing.wellbeeing.service.telemedic;

import com.wellbeeing.wellbeeing.domain.telemedic.MeasureType;

import java.util.List;
import java.util.UUID;

public interface MeasureTypeService {
    List<MeasureType> getMeasureTypes();
    MeasureType getMeasureTypeById(UUID measureTypeId);
    MeasureType addMeasureType(MeasureType measureType);
    MeasureType updateMeasureType(MeasureType updatedMeasureType);
    boolean deleteMeasureTypeById(UUID measureTypeId);
}
