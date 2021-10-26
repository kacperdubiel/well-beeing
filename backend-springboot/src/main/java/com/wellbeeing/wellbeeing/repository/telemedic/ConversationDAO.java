package com.wellbeeing.wellbeeing.repository.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.telemedic.Conversation;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("conversationDAO")
public interface ConversationDAO extends JpaRepository<Conversation, UUID> {
    List<Conversation> findByFirstProfileAndConnectionType(Profile firstProfile, EConnectionType connectionType);
    List<Conversation> findBySecondProfileAndConnectionType(Profile secondProfile, EConnectionType connectionType);
    Conversation findByFirstProfileAndSecondProfileAndConnectionType(Profile firstProfile, Profile secondProfile, EConnectionType connectionType);
}
