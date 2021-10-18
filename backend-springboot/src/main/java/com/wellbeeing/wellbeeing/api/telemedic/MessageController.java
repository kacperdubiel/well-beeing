package com.wellbeeing.wellbeeing.api.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import com.wellbeeing.wellbeeing.domain.telemedic.Message;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import com.wellbeeing.wellbeeing.service.telemedic.MessageServiceApi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MessageController {
    private MessageServiceApi messageService;
    private ProfileService profileService;

    public MessageController(
            @Qualifier("messageService") MessageServiceApi messageService,
            @Qualifier("profileService") ProfileService profileService){
        this.messageService = messageService;
        this.profileService = profileService;
    }

    @RequestMapping(path = "messages/{id}", method = RequestMethod.GET)
    public ResponseEntity<Message> getMessageById(@PathVariable("id") UUID id){
        Message messageResult = messageService.getMessageById(id);
        if(messageResult == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(messageResult, HttpStatus.OK);
    }

    @RequestMapping(path = "messages/sender/{sender_id}/receiver/{receiver_id}/type/{type}", method = RequestMethod.GET)
    public ResponseEntity<List<Message>> getMessageBySenderAndReceiverAndConnectionType(
            @PathVariable("sender_id") UUID senderId,
            @PathVariable("receiver_id") UUID receiverId,
            @PathVariable("type") EConnectionType connectionType
    ){
        Profile sender = profileService.getProfileById(senderId);
        Profile receiver = profileService.getProfileById(receiverId);
        List<Message> messages = messageService.getMessageBySenderAndReceiverAndConnectionType(sender, receiver, connectionType);

        if(messages == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else {
            return new ResponseEntity<>(messages, HttpStatus.OK);
        }
    }

    @RequestMapping(path = "messages", method = RequestMethod.POST)
    public ResponseEntity<Message> addMessage(@RequestBody @NonNull Message message){
        Message messageResult = messageService.addMessage(message);
        return new ResponseEntity<>(messageResult, HttpStatus.CREATED);
    }

    @RequestMapping(path = "messages", method = RequestMethod.PUT)
    public ResponseEntity<Message> updateMessage(@RequestBody @NonNull Message updatedMessage){
        Message messageResult = messageService.updateMessage(updatedMessage);
        if(messageResult != null)
            return new ResponseEntity<>(messageResult, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
