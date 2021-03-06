package com.wellbeeing.wellbeeing.api.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.IllegalArgumentException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.message.PaginatedResponse;
import com.wellbeeing.wellbeeing.domain.telemedic.Conversation;
import com.wellbeeing.wellbeeing.domain.telemedic.EMessageType;
import com.wellbeeing.wellbeeing.domain.telemedic.Message;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import com.wellbeeing.wellbeeing.service.account.UserService;
import com.wellbeeing.wellbeeing.service.files.FileService;
import com.wellbeeing.wellbeeing.service.telemedic.ConversationService;
import com.wellbeeing.wellbeeing.service.telemedic.MessageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MessageController {
    private MessageService messageService;
    private UserService userService;
    private ConversationService conversationService;
    private ProfileService profileService;
    private FileService fileService;

    public MessageController(@Qualifier("messageService") MessageService messageService,
                             @Qualifier("userService") UserService userService,
                             @Qualifier("conversationService") ConversationService conversationService,
                             @Qualifier("profileService") ProfileService profileService,
                             @Qualifier("fileService") FileService fileService){
        this.messageService = messageService;
        this.userService = userService;
        this.conversationService = conversationService;
        this.profileService = profileService;
        this.fileService = fileService;
    }

    @RequestMapping(path = "conversations/{id}/messages", method = RequestMethod.GET)
    public ResponseEntity<?> getConversationMessages(@PathVariable("id") UUID id, Principal principal,
                                                     @RequestParam(value = "page", defaultValue = "0") String page,
                                                     @RequestParam(value = "size", defaultValue = "10") String size)
            throws NotFoundException, ForbiddenException
    {
        UUID userId = userService.findUserIdByUsername(principal.getName());
        Conversation conversation = conversationService.getConversationById(id);

        UUID firstConvProfileId = conversation.getFirstProfile().getId();
        UUID secondConvProfileId = conversation.getSecondProfile().getId();

        if(!firstConvProfileId.equals(userId) && !secondConvProfileId.equals(userId)){
            throw new ForbiddenException("You do not have access rights to do that!");
        }

        Page<Message> messagesPage = messageService.getMessagesByConversation(conversation, Integer.parseInt(page), Integer.parseInt(size));
        PaginatedResponse response = PaginatedResponse.builder()
                .currentPage(messagesPage.getNumber())
                .totalItems(messagesPage.getTotalElements())
                .totalPages(messagesPage.getTotalPages())
                .objects(messagesPage.getContent())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(path = "messages/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getMessageById(@PathVariable("id") UUID id, Principal principal)
            throws NotFoundException, ForbiddenException
    {
        UUID userId = userService.findUserIdByUsername(principal.getName());
        Message message = messageService.getMessageById(id);
        Conversation conversation = message.getConversation();
        UUID firstConvProfileId = conversation.getFirstProfile().getId();
        UUID secondConvProfileId = conversation.getSecondProfile().getId();

        if(!firstConvProfileId.equals(userId) && !secondConvProfileId.equals(userId)){
            throw new ForbiddenException("You do not have access rights to do that!");
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(path = "messages", method = RequestMethod.POST)
    public ResponseEntity<?> addMessage(@RequestBody @NonNull Message message, Principal principal)
            throws ForbiddenException, ConflictException, NotFoundException
    {
        UUID userId = userService.findUserIdByUsername(principal.getName());
        Profile sender = profileService.getProfileById(userId);
        message.setSender(sender);

        UUID conversationId = message.getConversation().getId();
        Conversation conversation = conversationService.getConversationById(conversationId);

        UUID firstConvProfileId = conversation.getFirstProfile().getId();
        UUID secondConvProfileId = conversation.getSecondProfile().getId();

        if(!firstConvProfileId.equals(userId) && !secondConvProfileId.equals(userId)){
            throw new ForbiddenException("You do not have access rights to do that!");
        }

        Message messageResult = messageService.addMessage(message);
        return new ResponseEntity<>(messageResult, HttpStatus.CREATED);
    }

    @PostMapping("/messages/import/{messageId}")
    public ResponseEntity<?> importData(MultipartFile file, @PathVariable("messageId") UUID messageId, Principal principal) throws NotFoundException, ForbiddenException {
        Message message = messageService.getMessageById(messageId);

        UUID userId = userService.findUserIdByUsername(principal.getName());
        if(!message.getSender().getId().equals(userId)){
            throw new ForbiddenException("You do not have access rights to do that!");
        }

        String fileName = fileService.save(file);
        message.setData(fileName);
        messageService.updateMessage(message);

        return new ResponseEntity<>("Message updated", HttpStatus.OK);
    }

    @GetMapping("/messages/export/{messageId}")
    public ResponseEntity<?> exportData(@PathVariable("messageId") UUID messageId) throws NotFoundException, IllegalArgumentException {
        Message message = messageService.getMessageById(messageId);

        if(message.getMessageType() != EMessageType.IMAGE){
            throw new IllegalArgumentException("Wrong image message.");
        }

        Resource file = fileService.load(message.getData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "messagePhoto")
                .contentType(MediaType.parseMediaType("image/png"))
                .body(file);
    }
}
