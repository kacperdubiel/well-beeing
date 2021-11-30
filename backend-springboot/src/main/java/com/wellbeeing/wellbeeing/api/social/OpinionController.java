package com.wellbeeing.wellbeeing.api.social;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.social.Opinion;
import com.wellbeeing.wellbeeing.domain.social.Post;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import com.wellbeeing.wellbeeing.service.account.UserService;
import com.wellbeeing.wellbeeing.service.files.FileService;
import com.wellbeeing.wellbeeing.service.social.LikeService;
import com.wellbeeing.wellbeeing.service.social.OpinionService;
import com.wellbeeing.wellbeeing.service.social.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class OpinionController {

    @Autowired
    private OpinionService opinionService;
    private final UserService userService;
    private final ProfileService profileService;

    public OpinionController(@Qualifier("opinionService") OpinionService opinionService,
                          @Qualifier("userService") UserService userService,
                          @Qualifier("profileService") ProfileService profileService) {
        this.opinionService = opinionService;
        this.userService = userService;
        this.profileService = profileService;
    }

    @GetMapping(path = "/opinions/{userId}")
    public ResponseEntity<?> getSpecialistOpinions(@PageableDefault(sort = {"addedDate"}, size = 20, direction = Sort.Direction.DESC) Pageable pageable, @PathVariable UUID userId) throws NotFoundException {
        Profile profile = profileService.getProfileById(userId);
        Page<Opinion> pageOpinions = opinionService.getSpecialistOpinions(profile, pageable);
        return new ResponseEntity<>(pageOpinions, HttpStatus.OK);
    }

    @PostMapping(path = "/opinion/{profileId}")
    public ResponseEntity<?> addOpinion(@PathVariable UUID profileId, @RequestBody @NonNull Opinion opinion, Principal principal) throws ForbiddenException, NotFoundException {
        Profile receiver = profileService.getProfileById(profileId);
        Opinion newOpinion = opinionService.addOpinion(principal.getName(), receiver, opinion);
        return new ResponseEntity<>(newOpinion, HttpStatus.OK);
    }

    @PatchMapping(path = "/opinion/{opinionId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateOpinion(@PathVariable long opinionId, @RequestBody Map<String, Object> fields, Principal principal) throws NotFoundException, ForbiddenException {
        Opinion newOpinion = opinionService.partialUpdateOpinion(opinionId, fields, principal.getName());
        return new ResponseEntity<>(newOpinion, HttpStatus.OK);
    }

    @PatchMapping(path = "/opinion/{opinionId}/delete")
    public ResponseEntity<?> deleteOpinion(@PathVariable long opinionId, Principal principal) throws NotFoundException, ForbiddenException {
        opinionService.deleteOpinion(opinionId, principal.getName());
        Opinion deletedOpinion = opinionService.getOpinion(opinionId);
        return new ResponseEntity<>(deletedOpinion, HttpStatus.OK);
    }
}
