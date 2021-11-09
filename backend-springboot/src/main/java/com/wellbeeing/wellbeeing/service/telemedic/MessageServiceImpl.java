package com.wellbeeing.wellbeeing.service.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.telemedic.Conversation;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import com.wellbeeing.wellbeeing.domain.telemedic.Message;
import com.wellbeeing.wellbeeing.domain.telemedic.ProfileConnection;
import com.wellbeeing.wellbeeing.repository.telemedic.MessageDAO;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service("messageService")
public class MessageServiceImpl implements MessageService {
    private MessageDAO messageDAO;
    private ConversationService conversationService;
    private ProfileService profileService;
    private ProfileConnectionService profileConnService;

    @Autowired
    public MessageServiceImpl(@Qualifier("messageDAO") MessageDAO messageDAO,
                              @Qualifier("conversationService") ConversationService conversationService,
                              @Qualifier("profileService") ProfileService profileService,
                              @Qualifier("profileConnectionService") ProfileConnectionService profileConnService){
        this.messageDAO = messageDAO;
        this.conversationService = conversationService;
        this.profileService = profileService;
        this.profileConnService = profileConnService;
    }

    @Override
    public Message getMessageById(UUID messageId) throws NotFoundException {
        Message message = messageDAO.findById(messageId).orElse(null);
        if(message == null) {
            throw new NotFoundException("Measure with id: " + messageId + " not found");
        }

        return message;
    }

    @Override
    public Page<Message> getMessagesByConversation(Conversation conversation, int page, int pageSize)
            throws NotFoundException
    {
        if(conversation.getId() == null){
            throw new NotFoundException("Specify conversation id!");
        }
        conversation = conversationService.getConversationById(conversation.getId());

        return messageDAO.findMessagesByConversation(conversation, PageRequest.of(page, pageSize,
                Sort.by(Sort.Direction.DESC, "createDate")));
    }

    @Override
    public Message addMessage(Message message) throws ConflictException, NotFoundException, ForbiddenException {
        UUID messageId = message.getId();

        if(messageId != null){
            Message messageResult = messageDAO.findById(messageId).orElse(null);
            if(messageResult != null) {
                throw new ConflictException("Message with id: " + messageId + " already exists!");
            }
        }

        Conversation conversation = conversationService.getConversationById(message.getConversation().getId());

        UUID firstProfileId = conversation.getFirstProfile().getId();
        UUID secondProfileId = conversation.getSecondProfile().getId();

        Profile firstProfile = profileService.getProfileById(firstProfileId);
        Profile secondProfile = profileService.getProfileById(secondProfileId);
        EConnectionType connectionType = conversation.getConnectionType();

        if(connectionType != EConnectionType.WITH_USER){
            ProfileConnection pConn = profileConnService
                    .getProfileConnectionByProfileAndConnectedWithAndType(firstProfile, secondProfile, connectionType);

            if (pConn == null || !pConn.isAccepted()) {
                throw new ForbiddenException("You have to be on specialists user list!");
            }
        }

        Date createDate = new Date();
        message.setCreateDate(createDate);
        Message msgResult = messageDAO.save(message);

        if(message.getSender().getId().equals(firstProfileId)){
            conversationService.updateReadStatus(conversation, secondProfile, false);
        }else if(message.getSender().getId().equals(secondProfileId)){
            conversationService.updateReadStatus(conversation, firstProfile, false);
        }

        conversationService.updateLastMessageDate(conversation, createDate);
        return msgResult;
    }

}
