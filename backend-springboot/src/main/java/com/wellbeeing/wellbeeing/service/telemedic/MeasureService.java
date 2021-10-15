package com.wellbeeing.wellbeeing.service.telemedic;

import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.domain.telemedic.Measure;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.repository.telemedic.MeasureDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("measureService")
public class MeasureService implements MeasureServiceApi {
    private MeasureDAO measureDAO;
    private UserDAO userDAO;

    @Autowired
    public MeasureService(@Qualifier("measureDAO") MeasureDAO measureDAO, @Qualifier("userDAO") UserDAO userDAO){
        this.measureDAO = measureDAO;
        this.userDAO = userDAO;
    }

    @Override
    public Measure getMeasureById(UUID measureId) {
        return measureDAO.findById(measureId).orElse(null);
    }

    @Override
    public List<Measure> getUserMeasures(UUID userId) {
        User user = userDAO.findById(userId).orElse(null);
        if(user == null)
            return null;
        else
            return user.getProfile().getMeasures();
    }

    @Override
    public Measure addMeasure(Measure measure) {
        measure.setId(UUID.randomUUID());

        return measureDAO.save(measure);
    }

    @Override
    public Measure updateMeasure(Measure updatedMeasure) {
        Measure resultMeasure = measureDAO.findById(updatedMeasure.getId()).orElse(null);
        if(resultMeasure != null){
            resultMeasure.setValue(updatedMeasure.getValue());
            resultMeasure.setMeasureDate(updatedMeasure.getMeasureDate());
            resultMeasure.setMeasureType(updatedMeasure.getMeasureType());
            resultMeasure.setOwner(updatedMeasure.getOwner());
            return measureDAO.save(resultMeasure);
        }
        return null;
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
