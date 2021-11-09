package com.wellbeeing.wellbeeing.service.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.telemedic.Conversation;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import com.wellbeeing.wellbeeing.domain.telemedic.ProfileConnection;
import com.wellbeeing.wellbeeing.repository.telemedic.ConversationDAO;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service("conversationService")
public class ConversationServiceImpl implements ConversationService {
    private ConversationDAO conversationDAO;
    private ProfileService profileService;
    private ProfileConnectionService profileConnService;

    @Autowired
    public ConversationServiceImpl(@Qualifier("conversationDAO") ConversationDAO conversationDAO,
                                   @Qualifier("profileService") ProfileService profileService,
                                   @Qualifier("profileConnectionService") ProfileConnectionService profileConnService){
        this.conversationDAO = conversationDAO;
        this.profileService = profileService;
        this.profileConnService = profileConnService;
    }

    @Override
    public Conversation getConversationById(UUID conversationId) throws NotFoundException {
        Conversation conversation = conversationDAO.findById(conversationId).orElse(null);
        if(conversation == null) {
            throw new NotFoundException("Conversation with id: " + conversationId + " not found");
        }

        return conversation;
    }

    @Override
    public Page<Conversation> getConversationsByProfileAndConnectionType(Profile profile, EConnectionType connectionType,
                                                                         boolean asSpecialist, int page, int size)
    {
        if(connectionType == EConnectionType.WITH_USER){
            return conversationDAO.findByFirstOrSecondProfileAndConnectionType(profile, connectionType,
                    PageRequest.of(page, size));
        } else {
            if(!asSpecialist){
                return conversationDAO.findByFirstProfileAndConnectionType(profile, connectionType,
                        PageRequest.of(page, size));
            } else {
                return conversationDAO.findBySecondProfileAndConnectionType(profile, connectionType,
                        PageRequest.of(page, size));
            }
        }
    }

    @Override
    public Conversation getConversationByProfilesAndType(Profile profile1, Profile profile2, EConnectionType connectionType)
            throws NotFoundException
    {
        Conversation conversation = conversationDAO
                .findByProfilesAndConnectionType(profile1, profile2, connectionType);

        if(conversation == null) {
            throw new NotFoundException("Conversation between profile id " + profile1.getId() +
                    " and profile id " + profile2.getId() + " with type " + connectionType + " not found.");
        }

        return conversation;
    }

    @Override
    public Conversation getConversationByFirstProfileAndSecondProfileAndType(Profile profile1, Profile profile2, EConnectionType connectionType)
            throws NotFoundException
    {
        Conversation conversation = conversationDAO
                .findByFirstProfileAndSecondProfileAndConnectionType(profile1, profile2, connectionType);

        if(conversation == null) {
            throw new NotFoundException("Conversation between user id " + profile1.getId() +
                    " and specialist id " + profile2.getId() + " with type " + connectionType + " not found.");
        }

        return conversation;
    }

    @Override
    public Conversation addConversation(Conversation conversation) throws NotFoundException, ConflictException, ForbiddenException {
        UUID conversationId = conversation.getId();
        if(conversationId != null){
            Conversation conversationResult = conversationDAO.findById(conversationId).orElse(null);
            if(conversationResult != null) {
                throw new ConflictException("Conversation with id: " + conversationId + " already exists!");
            }
        }

        UUID firstProfileId = conversation.getFirstProfile().getId();
        UUID secondProfileId = conversation.getSecondProfile().getId();

        Profile firstProfile = profileService.getProfileById(firstProfileId);
        Profile secondProfile = profileService.getProfileById(secondProfileId);
        EConnectionType connectionType = conversation.getConnectionType();

        Conversation existingConversation = conversationDAO
                .findByFirstProfileAndSecondProfileAndConnectionType(firstProfile, secondProfile, connectionType);;
        if(conversation.getConnectionType() == EConnectionType.WITH_USER && existingConversation == null){
            existingConversation = conversationDAO.findByFirstProfileAndSecondProfileAndConnectionType(secondProfile,
                    firstProfile, connectionType);
        }
        if(existingConversation != null){
            throw new ConflictException("Conversation already exist!");
        }

        if(conversation.getConnectionType() != EConnectionType.WITH_USER) {
            ProfileConnection pConn = profileConnService
                    .getProfileConnectionByProfileAndConnectedWithAndType(firstProfile, secondProfile, connectionType);

            if (pConn == null || !pConn.isAccepted()) {
                throw new ForbiddenException("You have to be on specialists user list!");
            }
        }

        conversation.setReadByFirstUser(true);
        conversation.setReadBySecondUser(true);
        conversation.setLastMessageDate(null);
        return conversationDAO.save(conversation);
    }

    @Override
    public Conversation updateReadStatus(Conversation conversation, Profile profile, boolean isRead) throws NotFoundException {
        Conversation conversationResult = findConversation(conversation);

        UUID profileId = profile.getId();
        UUID firstProfileId = conversationResult.getFirstProfile().getId();
        UUID secondProfileId = conversationResult.getSecondProfile().getId();

        if(firstProfileId.equals(profileId)){
            conversationResult.setReadByFirstUser(isRead);
        } else if (secondProfileId.equals(profileId)){
            conversationResult.setReadBySecondUser(isRead);
        }
        return conversationDAO.save(conversationResult);
    }

    @Override
    public Conversation updateLastMessageDate(Conversation conversation, Date lastMessageDate) throws NotFoundException {
        Conversation conversationResult = findConversation(conversation);

        conversationResult.setLastMessageDate(lastMessageDate);

        return conversationDAO.save(conversationResult);
    }

    private Conversation findConversation(Conversation conversation) throws NotFoundException {
        UUID conversationId = conversation.getId();

        if(conversationId == null){
            throw new NotFoundException("Specify conversation id!");
        }

        Conversation conversationResult = conversationDAO.findById(conversationId).orElse(null);
        if(conversationResult == null) {
            throw new NotFoundException("Conversation with id: " + conversationId + " not found!");
        }
        return conversationResult;
    }
}
