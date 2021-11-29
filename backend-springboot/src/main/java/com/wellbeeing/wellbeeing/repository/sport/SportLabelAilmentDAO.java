package com.wellbeeing.wellbeeing.repository.sport;

import com.wellbeeing.wellbeeing.domain.sport.SportLabelAilment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("sportLabelAilmentDAO")
public interface SportLabelAilmentDAO extends JpaRepository<SportLabelAilment, Long> {
}
