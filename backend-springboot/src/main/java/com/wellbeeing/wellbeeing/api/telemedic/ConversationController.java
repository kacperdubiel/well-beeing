package com.wellbeeing.wellbeeing.api.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.message.PaginatedResponse;
import com.wellbeeing.wellbeeing.domain.telemedic.Conversation;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import com.wellbeeing.wellbeeing.service.account.UserService;
import com.wellbeeing.wellbeeing.service.telemedic.ConversationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @RequestMapping(path = "conversations/as-specialist/{as_specialist}/type/{type}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserConversationsByType(@PathVariable("as_specialist") boolean asSpecialist,
                                                        @PathVariable("type") String type, Principal principal,
                                                        @RequestParam(value = "page", defaultValue = "0") String page,
                                                        @RequestParam(value = "size", defaultValue = "10") String size)
            throws NotFoundException
    {
        EConnectionType connectionType = EConnectionType.valueOf(type);
        UUID userId = userService.findUserIdByUsername(principal.getName());
        Profile profile = profileService.getProfileById(userId);

        Page<Conversation> conversations = conversationService.getConversationsByProfileAndConnectionType(profile,
                connectionType, asSpecialist, Integer.parseInt(page), Integer.parseInt(size));
        PaginatedResponse response = PaginatedResponse.builder()
                .currentPage(conversations.getNumber())
                .totalItems(conversations.getTotalElements())
                .totalPages(conversations.getTotalPages())
                .objects(conversations.getContent())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(path = "conversations/profile/{profile_id}/type/{type}", method = RequestMethod.GET)
    public ResponseEntity<?> getConversationByProfileAndType(
            @PathVariable("profile_id") UUID otherProfileId,
            @PathVariable("type") String type,
            Principal principal
        ) throws NotFoundException
    {
        EConnectionType connectionType = EConnectionType.valueOf(type);
        UUID userId = userService.findUserIdByUsername(principal.getName());
        Profile userProfile = profileService.getProfileById(userId);

        Profile otherProfile = profileService.getProfileById(otherProfileId);
        Conversation conversation = conversationService.getConversationByProfilesAndType(userProfile, otherProfile, connectionType);
        return new ResponseEntity<>(conversation, HttpStatus.OK);
    }

    @RequestMapping(path = "conversations", method = RequestMethod.POST)
    public ResponseEntity<?> addConversation(@RequestBody @NonNull Conversation conversation, Principal principal)
            throws ForbiddenException, ConflictException, NotFoundException
    {
        UUID userId = userService.findUserIdByUsername(principal.getName());

        if(!conversation.getFirstProfile().getId().equals(userId) && !conversation.getSecondProfile().getId().equals(userId)){
            throw new ForbiddenException("You do not have access rights to do that!");
        }

        Conversation conversationResult = conversationService.addConversation(conversation);
        return new ResponseEntity<>(conversationResult, HttpStatus.CREATED);
    }

    @RequestMapping(path = "conversations/{id}/mark-as-read", method = RequestMethod.PUT)
    public ResponseEntity<?> markConversationAsRead(@PathVariable("id") UUID id, Principal principal)
            throws NotFoundException, ForbiddenException
    {
        UUID userId = userService.findUserIdByUsername(principal.getName());
        Profile userProfile = profileService.getProfileById(userId);
        Conversation conversation = conversationService.getConversationById(id);

        UUID convFirstUserId = conversation.getFirstProfile().getId();
        UUID convSecondUserId = conversation.getSecondProfile().getId();

        if(!convFirstUserId.equals(userId) && !convSecondUserId.equals(userId)){
            throw new ForbiddenException("You do not have access rights to do that!");
        }

        Conversation conversationResult = conversationService.updateReadStatus(conversation, userProfile, true);
        return new ResponseEntity<>(conversationResult, HttpStatus.OK);
    }

}
