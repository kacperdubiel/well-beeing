package com.wellbeeing.wellbeeing.service.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import com.wellbeeing.wellbeeing.domain.telemedic.Message;

import java.util.List;
import java.util.UUID;

public interface MessageServiceApi {
    Message getMessageById(UUID messageId);
    List<Message> getMessageBySenderAndReceiverAndConnectionType(Profile sender, Profile receiver, EConnectionType connectionType);
    Message addMessage(Message message);
    Message updateMessage(Message updatedMessage);
}
