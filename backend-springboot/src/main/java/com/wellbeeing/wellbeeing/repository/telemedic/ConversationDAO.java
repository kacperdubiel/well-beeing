package com.wellbeeing.wellbeeing.repository.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.telemedic.Conversation;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("conversationDAO")
public interface ConversationDAO extends JpaRepository<Conversation, UUID> {
    @Query("select c from Conversation c " +
            "where (c.firstProfile = :profile or c.secondProfile = :profile) " +
            "and c.connectionType = :connType ")
    Page<Conversation> findByFirstOrSecondProfileAndConnectionType(
            @Param("profile") Profile profile, @Param("connType") EConnectionType connectionType, Pageable pageable);

    Page<Conversation> findByFirstProfileAndConnectionType(Profile firstProfile, EConnectionType connectionType, Pageable pageable);
    Page<Conversation> findBySecondProfileAndConnectionType(Profile secondProfile, EConnectionType connectionType, Pageable pageable);
    Conversation findByFirstProfileAndSecondProfileAndConnectionType(Profile firstProfile, Profile secondProfile,
                                                                     EConnectionType connectionType);
}
