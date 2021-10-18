package com.wellbeeing.wellbeeing.api.telemedic;

import com.wellbeeing.wellbeeing.domain.telemedic.Message;
import com.wellbeeing.wellbeeing.service.telemedic.MessageServiceApi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MessageController {
    private MessageServiceApi messageService;

    public MessageController(@Qualifier("messageService") MessageServiceApi messageService){
        this.messageService = messageService;
    }

    @RequestMapping(path = "messages/{id}", method = RequestMethod.GET)
    public ResponseEntity<Message> getMessageById(@PathVariable("id") UUID id){
        Message messageResult = messageService.getMessageById(id);
        if(messageResult == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(messageResult, HttpStatus.OK);
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
