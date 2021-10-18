package com.wellbeeing.wellbeeing.repository.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.telemedic.Conversation;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("conversationDAO")
public interface ConversationDAO extends JpaRepository<Conversation, UUID> {
    Conversation findByFirstProfileAndSecondProfileAndConnectionType(Profile firstProfile, Profile secondProfile, EConnectionType connectionType);
}
