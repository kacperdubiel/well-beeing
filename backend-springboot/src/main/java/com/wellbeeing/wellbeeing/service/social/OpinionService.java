package com.wellbeeing.wellbeeing.service.social;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.social.Opinion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface OpinionService {
    Page<Opinion> getSpecialistOpinions(Profile receiver, Pageable pageable);
    Opinion getOpinion(long opinionId);
    Opinion getMyOpinionToSpecialist(Profile specialist, String myName) throws ForbiddenException;

    Opinion addOpinion(String creatorName, Profile receiver, Opinion opinion) throws ForbiddenException;
    Opinion partialUpdateOpinion(long opinionId, Map<String, Object> fields, String updaterName) throws NotFoundException, ForbiddenException;
    boolean deleteOpinion(long opinionId, String cancellerName) throws NotFoundException, ForbiddenException;
}
