package com.wellbeeing.wellbeeing.repository.social;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.social.Opinion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("opinionDAO")
public interface OpinionDAO extends JpaRepository<Opinion, Long> {
    Page<Opinion> findAllByReceiverAndIsDeleted(Profile receiver, boolean isDeleted, Pageable pageable);
    Optional<Opinion> findByGiverAndReceiver(Profile giver, Profile receiver);
    Optional<Opinion> findOpinionByOpinionId(long opinionId);

}
