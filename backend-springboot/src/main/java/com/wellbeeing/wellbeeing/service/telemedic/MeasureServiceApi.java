package com.wellbeeing.wellbeeing.service.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.telemedic.Measure;
import com.wellbeeing.wellbeeing.domain.telemedic.MeasureType;

import java.util.List;
import java.util.UUID;

public interface MeasureServiceApi {
    Measure getMeasureById(UUID measureId);
    List<Measure> getMeasuresByProfileAndMeasureType(Profile profile, MeasureType measureType);
    Measure addMeasure(Measure measure);
    Measure updateMeasure(Measure updatedMeasure);
    boolean deleteMeasureById(UUID measureId);
}
