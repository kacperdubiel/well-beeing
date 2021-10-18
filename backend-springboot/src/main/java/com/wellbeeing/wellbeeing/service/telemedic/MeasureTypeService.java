package com.wellbeeing.wellbeeing.service.telemedic;

import com.wellbeeing.wellbeeing.domain.telemedic.MeasureType;
import com.wellbeeing.wellbeeing.repository.telemedic.MeasureTypeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("measureTypeService")
public class MeasureTypeService implements MeasureTypeServiceApi {
    private MeasureTypeDAO measureTypeDAO;

    @Autowired
    public MeasureTypeService(@Qualifier("measureTypeDAO") MeasureTypeDAO measureTypeDAO){
        this.measureTypeDAO = measureTypeDAO;
    }

    @Override
    public List<MeasureType> getMeasureTypes(){
        return measureTypeDAO.findAll();
    }

    @Override
    public MeasureType getMeasureTypeById(UUID measureTypeId) {
        return measureTypeDAO.findById(measureTypeId).orElse(null);
    }

    @Override
    public MeasureType addMeasureType(MeasureType measureType) {
        return measureTypeDAO.save(measureType);
    }

    @Override
    public MeasureType updateMeasureType(MeasureType updatedMeasureType) {
        return measureTypeDAO.save(updatedMeasureType);
    }

    @Override
    public boolean deleteMeasureTypeById(UUID measureTypeId) {
        if(measureTypeDAO.findById(measureTypeId).isPresent()){
            measureTypeDAO.deleteById(measureTypeId);
            return true;
        }
        return false;
    }
}
