package com.wellbeeing.wellbeeing.service.social;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.social.Opinion;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.repository.social.OpinionDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;

@Service("opinionService")
public class OpinionServiceImpl implements OpinionService {

    private final OpinionDAO opinionDAO;
    private final UserDAO userDAO;

    public OpinionServiceImpl(@Qualifier("opinionDAO") OpinionDAO opinionDAO,
                           @Qualifier("userDAO") UserDAO userDAO) {
        this.opinionDAO = opinionDAO;
        this.userDAO = userDAO;
    }

    @Override
    public Page<Opinion> getSpecialistOpinions(Profile receiver, Pageable pageable) {
        return opinionDAO.findAllByReceiverAndIsDeleted(receiver, false, pageable);
    }

    @Override
    public Opinion getOpinion(long opinionId) {
        return opinionDAO.findOpinionByOpinionId(opinionId).orElse(null);
    }

    @Override
    public Opinion getMyOpinionToSpecialist(Profile specialist, String myName) throws ForbiddenException {
        User user = userDAO.findUserByEmail(myName).orElse(null);
        if (user == null)
            throw new UsernameNotFoundException("User: " + myName + " not found");

        if (specialist.getDoctorProfile() == null && specialist.getDieticianProfile() == null && specialist.getTrainerProfile() == null)
            throw new ForbiddenException("This user is not a specialist!");

        return opinionDAO.findByGiverAndReceiver(user.getProfile(), specialist).orElse(null);

    }

    @Override
    public Opinion addOpinion(String creatorName, Profile receiver, Opinion opinion) throws ForbiddenException {
        User user = userDAO.findUserByEmail(creatorName).orElse(null);
        if (user == null)
            throw new UsernameNotFoundException("User: " + creatorName + " not found");

        if (receiver.getDoctorProfile() == null && receiver.getDieticianProfile() == null && receiver.getTrainerProfile() == null)
            throw new ForbiddenException("This user is not a specialist!");

        Opinion existingOpinion = opinionDAO.findByGiverAndReceiver(user.getProfile(), receiver).orElse(null);
        if (existingOpinion != null && !existingOpinion.isDeleted())
            throw new ForbiddenException("You have already given the opinion to this specialist!");
        else if (existingOpinion != null && existingOpinion.isDeleted()) {
            existingOpinion.setOpinionContent(opinion.getOpinionContent());
            existingOpinion.setRating(opinion.getRating());
            existingOpinion.setDeleted(false);
            existingOpinion.setAddedDate(new Date());
            opinionDAO.save(existingOpinion);
            return existingOpinion;
        }
        else {
            opinion.setGiver(user.getProfile());
            opinion.setReceiver(receiver);
            opinionDAO.save(opinion);
            return opinion;
        }
    }

    @Override
    public Opinion partialUpdateOpinion(long opinionId, Map<String, Object> fields, String updaterName) throws NotFoundException, ForbiddenException {
        Opinion targetOpinion = opinionDAO.findOpinionByOpinionId(opinionId).orElse(null);

        if (targetOpinion == null || targetOpinion.isDeleted() || fields == null || fields.isEmpty())
            throw new NotFoundException(String.format("There's no opinion with id=%d", opinionId));

        Profile updaterProfile = userDAO.findUserByEmail(updaterName).orElse(null).getProfile();
        Profile opinionOwner = targetOpinion.getGiver();

        if (updaterProfile != opinionOwner)
            throw new ForbiddenException("That is not your opinion!");

        fields.remove("opinionId");
        fields.remove("addedDate");
        fields.remove("isDeleted");
        fields.remove("deleted");
        fields.remove("giver");
        fields.remove("receiver");

        fields.forEach((k, v) -> {

            Field field = ReflectionUtils.findField(Opinion.class, k);

            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, targetOpinion, v);
            }
        });

        opinionDAO.save(targetOpinion);
        return targetOpinion;
    }

    @Override
    public boolean deleteOpinion(long opinionId, String cancellerName) throws NotFoundException, ForbiddenException {
        Opinion targetOpinion = opinionDAO.findOpinionByOpinionId(opinionId).orElse(null);

        if (targetOpinion == null || targetOpinion.isDeleted())
            throw new NotFoundException(String.format("There's no opinion with id=%d", opinionId));

        Profile cancellerProfile = userDAO.findUserByEmail(cancellerName).orElse(null).getProfile();
        Profile opinionOwner = targetOpinion.getGiver();

        if (cancellerProfile != opinionOwner)
            throw new ForbiddenException("That is not your opinion!");

        targetOpinion.setDeleted(true);
        opinionDAO.save(targetOpinion);
        return true;
    }
}
