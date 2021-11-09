package com.wellbeeing.wellbeeing.service.telemedic;

import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.telemedic.Conversation;
import com.wellbeeing.wellbeeing.domain.telemedic.Message;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface MessageService {
    Message getMessageById(UUID messageId) throws NotFoundException;
    Page<Message> getMessagesByConversation(Conversation conversation, int page, int pageSize) throws NotFoundException;
    Message addMessage(Message message) throws ConflictException, NotFoundException, ForbiddenException;
}
