package com.wellbeeing.wellbeeing.service.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.IllegalArgumentException;
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
    private MeasureTypeService measureTypeService;

    @Autowired
    public MeasureServiceImpl(@Qualifier("measureDAO") MeasureDAO measureDAO,
                              @Qualifier("measureTypeService") MeasureTypeService measureTypeService){
        this.measureDAO = measureDAO;
        this.measureTypeService = measureTypeService;
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
    public Measure addMeasure(Measure measure) throws ConflictException, NotFoundException, IllegalArgumentException {
        UUID measureId = measure.getId();
        if(measureId != null){
            Measure measureResult = measureDAO.findById(measureId).orElse(null);
            if(measureResult != null) {
                throw new ConflictException("Measure with id: " + measureId + " already exists!");
            }
        }

        MeasureType measureType = measureTypeService.getMeasureTypeById(measure.getMeasureType().getId());
        if(measure.getValue() < measureType.getMinValue() || measure.getValue() > measureType.getMaxValue()){
            throw new IllegalArgumentException("Measure value is invalid!");
        }

        return measureDAO.save(measure);
    }

    @Override
    public Measure updateMeasure(Measure updatedMeasure) throws NotFoundException, IllegalArgumentException {
        UUID measureId = updatedMeasure.getId();

        if(measureId == null){
            throw new NotFoundException("Specify measure id!");
        }

        Measure measureResult = measureDAO.findById(measureId).orElse(null);
        if(measureResult == null) {
            throw new NotFoundException("Measure with id: " + measureId + " not found!");
        }

        MeasureType measureType = measureTypeService.getMeasureTypeById(updatedMeasure.getMeasureType().getId());
        if(updatedMeasure.getValue() < measureType.getMinValue() || updatedMeasure.getValue() > measureType.getMaxValue()){
            throw new IllegalArgumentException("Measure value is invalid!");
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
