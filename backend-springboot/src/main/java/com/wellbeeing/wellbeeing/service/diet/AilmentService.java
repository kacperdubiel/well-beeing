package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.diet.Ailment;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface AilmentService {
    Ailment getAilmentById(UUID ailmentId) throws NotFoundException;
    List<Ailment> getAllAilments();
}
