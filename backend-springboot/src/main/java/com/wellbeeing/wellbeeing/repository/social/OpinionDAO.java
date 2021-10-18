package com.wellbeeing.wellbeeing.repository.social;

import com.wellbeeing.wellbeeing.domain.social.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("opinionDAO")
public interface OpinionDAO extends JpaRepository<Opinion, Long> {
    List<Opinion> findAllByGiver_Id(UUID giver_id);
    List<Opinion> findAllByReceiver_Id(UUID receiver_id);

}
