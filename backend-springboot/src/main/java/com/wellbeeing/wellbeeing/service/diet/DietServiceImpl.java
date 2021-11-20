package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.diet.Diet;
import com.wellbeeing.wellbeeing.repository.diet.DietDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dietService")
public class DietServiceImpl implements DietService {
    private final DietDAO dietDAO;

    public DietServiceImpl(@Qualifier("dietDAO") DietDAO dietDAO){
        this.dietDAO = dietDAO;
    }

    @Override
    public List<Diet> getAllDiets() {
        return this.dietDAO.findAll();
    }
}
