package com.wellbeeing.wellbeeing.repository.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.telemedic.Measure;
import com.wellbeeing.wellbeeing.domain.telemedic.ProfileConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("measureDAO")
public interface MeasureDAO extends JpaRepository<Measure, UUID> {
    List<Measure> findByOwner(Profile owner);
}
