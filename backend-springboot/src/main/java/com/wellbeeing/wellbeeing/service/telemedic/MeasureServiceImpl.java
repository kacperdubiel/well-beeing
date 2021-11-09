package com.wellbeeing.wellbeeing.service.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.telemedic.Measure;
import com.wellbeeing.wellbeeing.domain.telemedic.MeasureType;
import com.wellbeeing.wellbeeing.repository.telemedic.MeasureDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("measureService")
public class MeasureServiceImpl implements MeasureService {
    private MeasureDAO measureDAO;

    @Autowired
    public MeasureServiceImpl(@Qualifier("measureDAO") MeasureDAO measureDAO){
        this.measureDAO = measureDAO;
    }

    @Override
    public Measure getMeasureById(UUID measureId) throws NotFoundException {
        Measure measure = measureDAO.findById(measureId).orElse(null);
        if(measure == null) {
            throw new NotFoundException("Measure with id: " + measureId + " not found");
        }

        return measure;
    }

    @Override
    public Page<Measure> getMeasuresByProfileAndMeasureType(Profile profile, MeasureType measureType, int page, int pageSize) {
        return measureDAO.findByOwnerAndMeasureType(profile, measureType, PageRequest.of(page, pageSize,
                Sort.by(Sort.Direction.DESC, "measureDate")));
    }

    @Override
    public Measure addMeasure(Measure measure) throws ConflictException {
        UUID measureId = measure.getId();
        if(measureId != null){
            Measure measureResult = measureDAO.findById(measureId).orElse(null);
            if(measureResult != null) {
                throw new ConflictException("Measure with id: " + measureId + " already exists!");
            }
        }

        return measureDAO.save(measure);
    }

    @Override
    public Measure updateMeasure(Measure updatedMeasure) throws NotFoundException {
        UUID measureId = updatedMeasure.getId();

        if(measureId == null){
            throw new NotFoundException("Specify measure id!");
        }

        Measure measureResult = measureDAO.findById(measureId).orElse(null);
        if(measureResult == null) {
            throw new NotFoundException("Measure with id: " + measureId + " not found!");
        }

        return measureDAO.save(updatedMeasure);
    }

    @Override
    public void deleteMeasureById(UUID measureId) throws NotFoundException {
        Measure measureResult = measureDAO.findById(measureId).orElse(null);
        if(measureResult == null) {
            throw new NotFoundException("Measure with id: " + measureId + " not found!");
        }

        measureDAO.deleteById(measureId);
    }
}
