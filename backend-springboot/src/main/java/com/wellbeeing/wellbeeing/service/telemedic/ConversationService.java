package com.wellbeeing.wellbeeing.service.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.telemedic.Conversation;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;

import java.util.List;
import java.util.UUID;

public interface ConversationService {
    Conversation getConversationById(UUID conversationId) throws NotFoundException;
    List<Conversation> getConversationsByProfileAndConnectionType(Profile profile, EConnectionType connectionType);
    Conversation getConversationByProfilesAndType(Profile profile1, Profile profile2, EConnectionType connectionType) throws NotFoundException;
    Conversation getConversationByFirstProfileAndSecondProfileAndType(Profile profile1, Profile profile2, EConnectionType connectionType) throws NotFoundException;
    Conversation addConversation(Conversation conversation) throws NotFoundException, ConflictException, ForbiddenException;
    Conversation updateReadStatus(Conversation conversation, Profile profile, boolean isRead) throws NotFoundException;
}
