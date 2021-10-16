package com.wellbeeing.wellbeeing.repository.sport;

import com.wellbeeing.wellbeeing.domain.SportLabel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("sportLabelDAO")
public interface SportLabelDAO extends JpaRepository<SportLabel, Long> {
}
