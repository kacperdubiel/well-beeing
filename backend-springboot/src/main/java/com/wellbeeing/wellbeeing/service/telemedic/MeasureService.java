package com.wellbeeing.wellbeeing.service.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.IllegalArgumentException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.telemedic.Measure;
import com.wellbeeing.wellbeeing.domain.telemedic.MeasureType;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface MeasureService {
    Measure getMeasureById(UUID measureId) throws NotFoundException;
    Page<Measure> getMeasuresByProfileAndMeasureType(Profile profile, MeasureType measureType, int page, int pageSize);
    Measure addMeasure(Measure measure) throws ConflictException, NotFoundException, IllegalArgumentException;
    Measure updateMeasure(Measure updatedMeasure) throws NotFoundException, IllegalArgumentException;
    void deleteMeasureById(UUID measureId) throws NotFoundException;
}
