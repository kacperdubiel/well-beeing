package com.wellbeeing.wellbeeing.service.sport;

import com.wellbeeing.wellbeeing.domain.SportLabel;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
public interface SportLabelService {
    public boolean addSportLabel(SportLabel sportLabel);
    public boolean deleteSportLabel(long id);
    public boolean getSportLabel(long id);
    public boolean updateSportLabel(SportLabel sportLabel);
}
