package com.wellbeeing.wellbeeing.service.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.telemedic.Measure;
import com.wellbeeing.wellbeeing.repository.account.ProfileDAO;
import com.wellbeeing.wellbeeing.repository.telemedic.MeasureDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("measureService")
public class MeasureService implements MeasureServiceApi {
    private MeasureDAO measureDAO;

    @Autowired
    public MeasureService(@Qualifier("measureDAO") MeasureDAO measureDAO){
        this.measureDAO = measureDAO;
    }

    @Override
    public Measure getMeasureById(UUID measureId) {
        return measureDAO.findById(measureId).orElse(null);
    }

    @Override
    public List<Measure> getMeasuresByProfile(Profile profile) {
        return measureDAO.findByOwner(profile);
    }

    @Override
    public Measure addMeasure(Measure measure) {
        measure.setId(UUID.randomUUID());

        return measureDAO.save(measure);
    }

    @Override
    public Measure updateMeasure(Measure updatedMeasure) {
        return measureDAO.save(updatedMeasure);
    }

    @Override
    public boolean deleteMeasureById(UUID measureId) {
        if(measureDAO.findById(measureId).isPresent()){
            measureDAO.deleteById(measureId);
            return true;
        }
        return false;
    }
}
