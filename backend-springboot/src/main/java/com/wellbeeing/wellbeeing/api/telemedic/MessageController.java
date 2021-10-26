package com.wellbeeing.wellbeeing.api.telemedic;

import com.wellbeeing.wellbeeing.domain.message.ErrorMessage;
import com.wellbeeing.wellbeeing.domain.telemedic.Measure;
import com.wellbeeing.wellbeeing.domain.telemedic.Message;
import com.wellbeeing.wellbeeing.service.account.UserService;
import com.wellbeeing.wellbeeing.service.telemedic.MessageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MessageController {
    private MessageService messageService;
    private UserService userService;

    public MessageController(@Qualifier("messageService") MessageService messageService,
                             @Qualifier("userService") UserService userService){
        this.messageService = messageService;
        this.userService = userService;
    }

    @RequestMapping(path = "messages/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getMessageById(@PathVariable("id") UUID id, Principal principal){
        UUID userId = userService.findUserIdByUsername(principal.getName());
        Message message = messageService.getMessageById(id);

        if(message == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        if(message.getSender().getId() != userId){
            return new ResponseEntity<>(new ErrorMessage("You do not have access rights to do that!",
                    "403"), HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(path = "messages", method = RequestMethod.POST)
    public ResponseEntity<?> addMessage(@RequestBody @NonNull Message message, Principal principal){
        UUID userId = userService.findUserIdByUsername(principal.getName());
        UUID convFirstUserId = message.getConversation().getFirstProfile().getId();
        UUID convSecondUserId = message.getConversation().getSecondProfile().getId();

        if(convFirstUserId != userId && convSecondUserId != userId){
            return new ResponseEntity<>(new ErrorMessage("You do not have access rights to do that!",
                    "403"), HttpStatus.FORBIDDEN);
        }

        Message messageResult = messageService.addMessage(message);
        return new ResponseEntity<>(messageResult, HttpStatus.CREATED);
    }

    @RequestMapping(path = "messages/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> markMessageAsRead(@PathVariable("id") UUID id, Principal principal){
        UUID userId = userService.findUserIdByUsername(principal.getName());
        Message message = messageService.getMessageById(id);

        if(message == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        UUID convFirstUserId = message.getConversation().getFirstProfile().getId();
        UUID convSecondUserId = message.getConversation().getSecondProfile().getId();

        if(convFirstUserId != userId && convSecondUserId != userId){
            return new ResponseEntity<>(new ErrorMessage("You do not have access rights to do that!",
                    "403"), HttpStatus.FORBIDDEN);
        }

        message.setRead(true);
        Message messageResult = messageService.updateMessage(message);
        if(messageResult != null)
            return new ResponseEntity<>(messageResult, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
