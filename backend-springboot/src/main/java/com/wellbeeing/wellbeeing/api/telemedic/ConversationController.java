package com.wellbeeing.wellbeeing.api.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.telemedic.Conversation;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import com.wellbeeing.wellbeeing.service.account.UserService;
import com.wellbeeing.wellbeeing.service.telemedic.ConversationService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ConversationController {
    private ConversationService conversationService;
    private UserService userService;
    private ProfileService profileService;

    public ConversationController(
            @Qualifier("conversationService") ConversationService conversationService,
            @Qualifier("userService") UserService userService,
            @Qualifier("profileService") ProfileService profileService
    ){
        this.conversationService = conversationService;
        this.userService = userService;
        this.profileService = profileService;
    }

    @RequestMapping(path = "conversations", method = RequestMethod.GET)
    public ResponseEntity<Conversation> getConversationById(@PathVariable("id") UUID id){
        Conversation conversationResult = conversationService.getConversationById(id);
        if(conversationResult == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(conversationResult, HttpStatus.OK);
    }

    @RequestMapping(path = "conversations/profile/{first_id}/profile/{second_id}/type/{type}", method = RequestMethod.GET)
    public ResponseEntity<Conversation> getConversationByProfile(
            @PathVariable("first_id") UUID firstProfileId,
            @PathVariable("second_id") UUID secondProfileId,
            @PathVariable("type") String connectionTypeText
    ){
        try {
            Profile profile1 = profileService.getProfileById(firstProfileId);
            Profile profile2 = profileService.getProfileById(secondProfileId);
            EConnectionType connectionType = EConnectionType.valueOf(connectionTypeText);

            Conversation conversation = conversationService.getConversationByProfilesAndType(profile1, profile2, connectionType);
            if(conversation == null)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            else {
                return new ResponseEntity<>(conversation, HttpStatus.OK);
            }
        } catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "conversations", method = RequestMethod.POST)
    public ResponseEntity<Conversation> addConversation(@RequestBody @NonNull Conversation conversation){
        Conversation conversationResult = conversationService.addConversation(conversation);
        return new ResponseEntity<>(conversationResult, HttpStatus.CREATED);
    }

    @RequestMapping(path = "conversations", method = RequestMethod.PUT)
    public ResponseEntity<Conversation> updateConversation(@RequestBody @NonNull Conversation updatedConversation){
        Conversation conversationResult = conversationService.updateConversation(updatedConversation);
        if(conversationResult != null)
            return new ResponseEntity<>(conversationResult, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "conversations/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteConversation(@PathVariable("id") UUID id){
        boolean deleteResult = conversationService.deleteConversationById(id);
        if(deleteResult)
            return new ResponseEntity<>(true, HttpStatus.OK);
        else
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }


}
