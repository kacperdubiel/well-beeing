package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.diet.Ailment;

import java.util.List;
import java.util.UUID;

public interface AilmentService {
    List<Ailment> getAllAilments();
    Ailment getAilmentById(UUID ailmentId);

}
