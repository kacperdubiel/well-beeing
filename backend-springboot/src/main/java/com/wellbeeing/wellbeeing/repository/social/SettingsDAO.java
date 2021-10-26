package com.wellbeeing.wellbeeing.repository.social;

import com.wellbeeing.wellbeeing.domain.social.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("settingsDAO")
public interface SettingsDAO extends JpaRepository<Settings, Long> {
    Settings findById(UUID id);
}
