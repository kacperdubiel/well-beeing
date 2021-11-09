package com.wellbeeing.wellbeeing.service.sport;

import com.wellbeeing.wellbeeing.domain.SportLabel;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
public interface SportLabelService {
    public SportLabel addSportLabel(SportLabel sportLabel);
    public boolean deleteSportLabel(long id);
    public SportLabel getSportLabel(long id);
    public SportLabel updateSportLabel(SportLabel sportLabel);
}
