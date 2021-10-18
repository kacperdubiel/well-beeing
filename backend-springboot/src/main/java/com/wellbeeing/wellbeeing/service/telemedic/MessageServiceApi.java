package com.wellbeeing.wellbeeing.service.telemedic;

import com.wellbeeing.wellbeeing.domain.telemedic.Message;

import java.util.UUID;

public interface MessageServiceApi {
    Message getMessageById(UUID messageId);
    Message addMessage(Message message);
    Message updateMessage(Message updatedMessage);
}
