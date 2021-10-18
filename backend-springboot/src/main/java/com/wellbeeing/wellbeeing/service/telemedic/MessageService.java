package com.wellbeeing.wellbeeing.service.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import com.wellbeeing.wellbeeing.domain.telemedic.Message;
import com.wellbeeing.wellbeeing.repository.telemedic.MessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("messageService")
public class MessageService implements MessageServiceApi {
    private MessageDAO messageDAO;

    @Autowired
    public MessageService(@Qualifier("messageDAO") MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    @Override
    public Message getMessageById(UUID messageId) {
        return messageDAO.findById(messageId).orElse(null);
    }

    @Override
    public Message addMessage(Message message) {
        return messageDAO.save(message);
    }

    @Override
    public Message updateMessage(Message updatedMessage) {
        return messageDAO.save(updatedMessage);
    }

}
