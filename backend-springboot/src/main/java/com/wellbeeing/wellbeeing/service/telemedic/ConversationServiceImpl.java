package com.wellbeeing.wellbeeing.service.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.telemedic.Conversation;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import com.wellbeeing.wellbeeing.repository.telemedic.ConversationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("conversationService")
public class ConversationServiceImpl implements ConversationService {
    private ConversationDAO conversationDAO;

    @Autowired
    public ConversationServiceImpl(@Qualifier("conversationDAO") ConversationDAO conversationDAO){
        this.conversationDAO = conversationDAO;
    }

    @Override
    public Conversation getConversationById(UUID conversationId) {
        return conversationDAO.findById(conversationId).orElse(null);
    }

    @Override
    public Conversation getConversationByProfilesAndType(Profile profile1, Profile profile2, EConnectionType connectionType) {
        Conversation conversation = conversationDAO
                .findByFirstProfileAndSecondProfileAndConnectionType(profile1, profile2, connectionType);
        if(conversation == null) {
            conversation = conversationDAO.findByFirstProfileAndSecondProfileAndConnectionType(profile2, profile1, connectionType);
        }
        return conversation;
    }

    @Override
    public Conversation addConversation(Conversation conversation) {
        return conversationDAO.save(conversation);
    }

    @Override
    public Conversation updateConversation(Conversation updatedConversation) {
        return conversationDAO.save(updatedConversation);
    }

    @Override
    public boolean deleteConversationById(UUID conversationId) {
        if(conversationDAO.findById(conversationId).isPresent()){
            conversationDAO.deleteById(conversationId);
            return true;
        }
        return false;
    }

}
