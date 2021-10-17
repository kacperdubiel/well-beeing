package com.wellbeeing.wellbeeing.repository.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import com.wellbeeing.wellbeeing.domain.telemedic.EMessageType;
import com.wellbeeing.wellbeeing.domain.telemedic.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("messageDAO")
public interface MessageDAO extends JpaRepository<Message, UUID> {
    List<Message> findBySenderAndReceiverAndConnectionType(Profile sender, Profile receiver, EConnectionType connectionType);
}
