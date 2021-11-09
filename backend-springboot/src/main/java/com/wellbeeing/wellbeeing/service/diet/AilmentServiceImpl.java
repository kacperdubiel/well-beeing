package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.diet.Ailment;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.repository.diet.AilmentDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("ailmentService")
public class AilmentServiceImpl implements AilmentService {
    private AilmentDAO ailmentDAO;

    public AilmentServiceImpl(@Qualifier("ailmentDAO") AilmentDAO ailmentDAO){
        this.ailmentDAO = ailmentDAO;
    }

    @Override
    public Ailment getAilmentById(UUID ailmentId) throws NotFoundException {
        Ailment ailment = ailmentDAO.findById(ailmentId).orElse(null);
        if(ailment != null)
            return ailment;
        throw new NotFoundException("Ailment with id: " +  ailmentId + " not found");
    }

    @Override
    public List<Ailment> getAllAilments() {
        return ailmentDAO.findAll();
    }
}
