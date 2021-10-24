package com.wellbeeing.wellbeeing.service.telemedic;

import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.telemedic.MeasureType;

import java.util.List;
import java.util.UUID;

public interface MeasureTypeService {
    List<MeasureType> getMeasureTypes();
    MeasureType getMeasureTypeById(UUID measureTypeId) throws NotFoundException;
    MeasureType addMeasureType(MeasureType measureType) throws ConflictException;
    MeasureType updateMeasureType(MeasureType updatedMeasureType) throws NotFoundException;
    void deleteMeasureTypeById(UUID measureTypeId) throws NotFoundException;
}
