package com.wellbeeing.wellbeeing.service.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.telemedic.Conversation;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import com.wellbeeing.wellbeeing.domain.telemedic.Message;
import com.wellbeeing.wellbeeing.domain.telemedic.ProfileConnection;

import java.util.List;
import java.util.UUID;

public interface ConversationServiceApi {
    Conversation getConversationById(UUID conversationId);
    Conversation getConversationByProfilesAndType(Profile profile1, Profile profile2, EConnectionType connectionType);
    Conversation addConversation(Conversation conversation);
    Conversation updateConversation(Conversation updatedConversation);
    boolean deleteConversationById(UUID conversationId);
}
