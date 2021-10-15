package com.wellbeeing.wellbeeing.repository.account;

import com.wellbeeing.wellbeeing.domain.account.ProfileCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("profileCardDAO")
public interface ProfileCardDAO extends JpaRepository<ProfileCard, UUID> {
}
