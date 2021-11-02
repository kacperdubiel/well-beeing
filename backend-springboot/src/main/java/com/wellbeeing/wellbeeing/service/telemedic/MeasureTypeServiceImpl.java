package com.wellbeeing.wellbeeing.service.telemedic;

import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.telemedic.MeasureType;
import com.wellbeeing.wellbeeing.repository.telemedic.MeasureTypeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("measureTypeService")
public class MeasureTypeServiceImpl implements MeasureTypeService {
    private MeasureTypeDAO measureTypeDAO;

    @Autowired
    public MeasureTypeServiceImpl(@Qualifier("measureTypeDAO") MeasureTypeDAO measureTypeDAO){
        this.measureTypeDAO = measureTypeDAO;
    }

    @Override
    public List<MeasureType> getMeasureTypes(){
        return measureTypeDAO.findAllByOrderByNameAsc();
    }

    @Override
    public MeasureType getMeasureTypeById(UUID measureTypeId) throws NotFoundException {
        MeasureType measureType = measureTypeDAO.findById(measureTypeId).orElse(null);
        if(measureType == null) {
            throw new NotFoundException("Measure type with id: " + measureTypeId +" not found!");
        }
        return measureType;
    }

    @Override
    public MeasureType addMeasureType(MeasureType measureType) throws ConflictException {
        UUID measureTypeId = measureType.getId();
        if(measureTypeId != null) {
            MeasureType measureTypeResult = measureTypeDAO.findById(measureTypeId).orElse(null);
            if (measureTypeResult != null) {
                throw new ConflictException("Measure type with id: " + measureTypeId + " already exists!");
            }
        }

        return measureTypeDAO.save(measureType);
    }

    @Override
    public MeasureType updateMeasureType(MeasureType updatedMeasureType) throws NotFoundException {
        UUID measureTypeId = updatedMeasureType.getId();

        if(measureTypeId == null) {
            throw new NotFoundException("Specify measure id!");
        }

        MeasureType measureTypeResult = measureTypeDAO.findById(measureTypeId).orElse(null);
        if(measureTypeResult == null) {
            throw new NotFoundException("Measure type with id: " + measureTypeId + " not found!");
        }

        return measureTypeDAO.save(updatedMeasureType);
    }

    @Override
    public void deleteMeasureTypeById(UUID measureTypeId) throws NotFoundException {
        MeasureType measureTypeResult = measureTypeDAO.findById(measureTypeId).orElse(null);
        if(measureTypeResult == null) {
            throw new NotFoundException("Measure type with id: " + measureTypeId + " not found!");
        }

        measureTypeDAO.deleteById(measureTypeId);
    }
}
