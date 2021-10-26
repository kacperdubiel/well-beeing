package com.wellbeeing.wellbeeing.service.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.telemedic.Conversation;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import com.wellbeeing.wellbeeing.repository.telemedic.ConversationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("conversationService")
public class ConversationServiceImpl implements ConversationService {
    private ConversationDAO conversationDAO;

    @Autowired
    public ConversationServiceImpl(@Qualifier("conversationDAO") ConversationDAO conversationDAO){
        this.conversationDAO = conversationDAO;
    }

    @Override
    public List<Conversation> getConversationsByProfileAndConnectionType(Profile profile, EConnectionType connectionType) {
        List<Conversation> convsAsFirstProfile = conversationDAO
                .findByFirstProfileAndConnectionType(profile, connectionType);
        List<Conversation> convsAsSecondProfile = conversationDAO
                .findBySecondProfileAndConnectionType(profile, connectionType);

        List<Conversation> mergedConversations = Stream.concat(convsAsFirstProfile.stream(), convsAsSecondProfile.stream())
                .collect(Collectors.toList());

        return mergedConversations;
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


}
