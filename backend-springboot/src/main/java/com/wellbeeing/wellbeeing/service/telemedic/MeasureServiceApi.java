package com.wellbeeing.wellbeeing.service.telemedic;

import com.wellbeeing.wellbeeing.domain.telemedic.Measure;

import java.util.List;
import java.util.UUID;

public interface MeasureServiceApi {
    Measure getMeasureById(UUID measureId);
    List<Measure> getUserMeasures(UUID userId);
    Measure addMeasure(Measure measure);
    Measure updateMeasure(Measure updatedMeasure);
    boolean deleteMeasureById(UUID measureId);
}
