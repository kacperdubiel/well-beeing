package com.wellbeeing.wellbeeing.api.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.message.ErrorMessage;
import com.wellbeeing.wellbeeing.domain.telemedic.Conversation;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import com.wellbeeing.wellbeeing.domain.telemedic.ProfileConnection;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import com.wellbeeing.wellbeeing.service.account.UserService;
import com.wellbeeing.wellbeeing.service.telemedic.ConversationService;
import com.wellbeeing.wellbeeing.service.telemedic.ProfileConnectionService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ConversationController {
    private ConversationService conversationService;
    private UserService userService;
    private ProfileService profileService;
    private ProfileConnectionService profileConnService;

    public ConversationController(
            @Qualifier("conversationService") ConversationService conversationService,
            @Qualifier("userService") UserService userService,
            @Qualifier("profileService") ProfileService profileService,
            @Qualifier("profileConnectionService") ProfileConnectionService profileConnService
    ){
        this.conversationService = conversationService;
        this.userService = userService;
        this.profileService = profileService;
        this.profileConnService = profileConnService;
    }

    @RequestMapping(path = "conversations/type/{type}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserConversationsByType(@PathVariable("type") String type, Principal principal){
        try {
            EConnectionType connectionType = EConnectionType.valueOf(type);
            UUID userId = userService.findUserIdByUsername(principal.getName());
            Profile profile = profileService.getProfileById(userId);

            List<Conversation> conversations = conversationService.getConversationsByProfileAndConnectionType(profile, connectionType);
            if(conversations == null)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>(conversations, HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ErrorMessage("Connection type not found",
                    "404"), HttpStatus.NOT_FOUND);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(new ErrorMessage("Not found: " + e.getMessage(),
                    "404"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorMessage("Server error: " + e.getMessage(),
                    "500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "conversations/profile/{profile_id}/type/{type}", method = RequestMethod.GET)
    public ResponseEntity<?> getConversationByProfile(
            @PathVariable("profile_id") UUID otherProfileId,
            @PathVariable("type") String type,
            Principal principal
    ){
        try {
            EConnectionType connectionType = EConnectionType.valueOf(type);
            UUID userId = userService.findUserIdByUsername(principal.getName());
            Profile userProfile = profileService.getProfileById(userId);

            Profile otherProfile = profileService.getProfileById(otherProfileId);
            Conversation conversation = conversationService.getConversationByProfilesAndType(userProfile, otherProfile, connectionType);
            return new ResponseEntity<>(conversation, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ErrorMessage("Connection type not found",
                    "404"), HttpStatus.NOT_FOUND);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(new ErrorMessage("Not found: " + e.getMessage(),
                    "404"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorMessage("Server error: " + e.getMessage(),
                    "500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "conversations", method = RequestMethod.POST)
    public ResponseEntity<?> addConversation(@RequestBody @NonNull Conversation conversation, Principal principal){
        // Check current user access rights
        UUID userId = userService.findUserIdByUsername(principal.getName());
        UUID firstProfileId = conversation.getFirstProfile().getId();
        UUID secondProfileId = conversation.getSecondProfile().getId();

        if(userId != firstProfileId && userId != secondProfileId){
            return new ResponseEntity<>(new ErrorMessage("You do not have access rights to do that!",
                    "403"), HttpStatus.FORBIDDEN);
        }

        // Check if conv already exists
        Profile firstProfile = conversation.getFirstProfile();
        Profile secondProfile = conversation.getSecondProfile();
        EConnectionType connectionType = conversation.getConnectionType();

        Conversation existingConversation = conversationService
                .getConversationByProfilesAndType(firstProfile, secondProfile, connectionType);

        if(existingConversation != null){
            return new ResponseEntity<>(new ErrorMessage("Converastion already exists!",
                    "409"), HttpStatus.CONFLICT);
        }

        // Check if user is on specialists user list
        if(connectionType != EConnectionType.WITH_USER){
            ProfileConnection pConn = profileConnService
                    .getProfileConnectionByProfileAndConnectedWithAndType(firstProfile, secondProfile, connectionType);

            if(pConn != null){
                return new ResponseEntity<>(new ErrorMessage("You have to be on specialists user list!",
                        "403"), HttpStatus.FORBIDDEN);
            }
        }

        // Adding conversation
        try {
            Conversation conversationResult = conversationService.addConversation(conversation);
            return new ResponseEntity<>(conversationResult, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorMessage("Server error: " + e.getMessage(),
                    "500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
