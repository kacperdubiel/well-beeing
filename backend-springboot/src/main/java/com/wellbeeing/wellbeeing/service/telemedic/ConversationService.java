package com.wellbeeing.wellbeeing.service.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.telemedic.Conversation;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import com.wellbeeing.wellbeeing.domain.telemedic.Message;
import com.wellbeeing.wellbeeing.domain.telemedic.ProfileConnection;

import java.util.List;
import java.util.UUID;

public interface ConversationService {
    List<Conversation> getConversationsByProfileAndConnectionType(Profile profile, EConnectionType connectionType);
    Conversation getConversationByProfilesAndType(Profile profile1, Profile profile2, EConnectionType connectionType);
    Conversation addConversation(Conversation conversation);
}
