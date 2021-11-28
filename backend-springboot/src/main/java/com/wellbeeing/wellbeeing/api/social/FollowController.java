package com.wellbeeing.wellbeeing.api.social;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.social.Follow;
import com.wellbeeing.wellbeeing.domain.social.Post;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import com.wellbeeing.wellbeeing.service.account.UserService;
import com.wellbeeing.wellbeeing.service.social.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class FollowController {

    @Autowired
    private FollowService followService;
    private final UserService userService;
    private final ProfileService profileService;

    public FollowController(@Qualifier("followService") FollowService followService,
                            @Qualifier("userService") UserService userService,
                            @Qualifier("profileService") ProfileService profileService) {
        this.followService = followService;
        this.userService = userService;
        this.profileService = profileService;
    }

    @GetMapping(path = "/followers/{profileId}")
    public ResponseEntity<?> getFollowers(@PathVariable UUID profileId,
            @PageableDefault(sort = {"addedDate"}, size = 20, direction = Sort.Direction.ASC) Pageable pageable) throws NotFoundException {
        Profile profile = profileService.getProfileById(profileId);
        Page<Follow> pageFollowers = followService.getFollowsByFollowed(profile, pageable);
        return new ResponseEntity<>(pageFollowers, HttpStatus.OK);
    }

    @GetMapping(path = "/following/{profileId}")
    public ResponseEntity<?> getFollowings(@PathVariable UUID profileId,
                                          @PageableDefault(sort = {"addedDate"}, size = 20, direction = Sort.Direction.ASC) Pageable pageable) throws NotFoundException {
        Profile profile = profileService.getProfileById(profileId);
        Page<Follow> pageFollowers = followService.getFollowsByFollower(profile, pageable);
        return new ResponseEntity<>(pageFollowers, HttpStatus.OK);
    }

    @PostMapping(path = "/follow/{profileId}")
    public ResponseEntity<?> addFollow(@PathVariable UUID profileId, Principal principal) throws NotFoundException, ForbiddenException {
        Profile followedProfile = profileService.getProfileById(profileId);
        Follow newFollow = followService.addFollow(followedProfile, principal.getName());
        return new ResponseEntity<>(newFollow, HttpStatus.OK);
    }

    @DeleteMapping(path = "/follow/{profileId}")
    public ResponseEntity<?> deleteFollow(@PathVariable UUID profileId, Principal principal) throws NotFoundException {
        Profile followedProfile = profileService.getProfileById(profileId);
        followService.deleteFollow(followedProfile, principal.getName());
        return new ResponseEntity<>("Successfully deleted following user with id=" + profileId, HttpStatus.OK);
    }

    @GetMapping(path = "/follow/{profileId}")
    public ResponseEntity<?> findFollow(@PathVariable UUID profileId, Principal principal) throws NotFoundException {
        Profile followedProfile = profileService.getProfileById(profileId);
        boolean returnValue = followService.findFollow(followedProfile, principal.getName());
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }
}
