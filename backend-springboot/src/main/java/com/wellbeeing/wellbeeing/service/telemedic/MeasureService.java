package com.wellbeeing.wellbeeing.service.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.telemedic.Measure;
import com.wellbeeing.wellbeeing.domain.telemedic.MeasureType;

import java.util.List;
import java.util.UUID;

public interface MeasureService {
    Measure getMeasureById(UUID measureId) throws NotFoundException;
    List<Measure> getMeasuresByProfileAndMeasureType(Profile profile, MeasureType measureType);
    Measure addMeasure(Measure measure) throws ConflictException;
    Measure updateMeasure(Measure updatedMeasure) throws NotFoundException;
    void deleteMeasureById(UUID measureId) throws NotFoundException;
}
