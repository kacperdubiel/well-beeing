package com.wellbeeing.wellbeeing.api.telemedic;

import com.wellbeeing.wellbeeing.domain.account.ERole;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.account.Role;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.message.PaginatedResponse;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import com.wellbeeing.wellbeeing.domain.telemedic.Message;
import com.wellbeeing.wellbeeing.domain.telemedic.ProfileConnection;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import com.wellbeeing.wellbeeing.service.account.UserService;
import com.wellbeeing.wellbeeing.service.telemedic.ProfileConnectionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ProfileConnectionController {
    private ProfileConnectionService profileConnectionService;
    private UserService userService;
    private ProfileService profileService;

    public ProfileConnectionController(
            @Qualifier("profileConnectionService") ProfileConnectionService profileConnectionService,
            @Qualifier("userService") UserService userService,
            @Qualifier("profileService") ProfileService profileService){
        this.profileConnectionService = profileConnectionService;
        this.userService = userService;
        this.profileService = profileService;
    }

    @RequestMapping(path = "profile-connections/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getProfileConnectionById(@PathVariable("id") UUID id, Principal principal) throws NotFoundException, ForbiddenException {
        UUID userId = userService.findUserIdByUsername(principal.getName());
        ProfileConnection pConnectionResult = profileConnectionService.getProfileConnectionById(id);

        if(!pConnectionResult.getProfile().getId().equals(userId)
            && !pConnectionResult.getConnectedWith().getId().equals(userId)){
            throw new ForbiddenException("You do not have access rights to do that!");
        }

        return new ResponseEntity<>(pConnectionResult, HttpStatus.OK);
    }

    // My connections
    @RequestMapping(path = "profile-connections/from-me/type/{type}/accepted/{value}", method = RequestMethod.GET)
    public ResponseEntity<?> getProfileConnectionsToType(@PathVariable("type") String connectionTypeText,
                                                         @PathVariable("value") boolean isAccepted, Principal principal,
                                                         @RequestParam(value = "page", defaultValue = "0") String page,
                                                         @RequestParam(value = "size", defaultValue = "10") String size)
            throws NotFoundException
    {
        UUID userId = userService.findUserIdByUsername(principal.getName());
        Profile profile = profileService.getProfileById(userId);
        EConnectionType connectionType = EConnectionType.valueOf(connectionTypeText);

        Page<ProfileConnection> profileConnsPage = profileConnectionService
                .getProfileConnectionsByProfileAndTypeAndIsAccepted(profile, connectionType, isAccepted,
                        Integer.parseInt(page), Integer.parseInt(size));
        PaginatedResponse response = PaginatedResponse.builder()
                .currentPage(profileConnsPage.getNumber())
                .totalItems(profileConnsPage.getTotalElements())
                .totalPages(profileConnsPage.getTotalPages())
                .objects(profileConnsPage.getContent())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Connected to me
    @RequestMapping(path = "profile-connections/to-me/type/{type}/accepted/{value}", method = RequestMethod.GET)
    public ResponseEntity<?> getProfileConnectionsFromType(@PathVariable("type") String connectionTypeText,
                                                           @PathVariable("value") boolean isAccepted, Principal principal,
                                                           @RequestParam(value = "page", defaultValue = "0") String page,
                                                           @RequestParam(value = "size", defaultValue = "10") String size)
            throws NotFoundException
    {
        UUID userId = userService.findUserIdByUsername(principal.getName());
        Profile connectedWith = profileService.getProfileById(userId);
        EConnectionType connectionType = EConnectionType.valueOf(connectionTypeText);

        Page<ProfileConnection> profileConnsPage = profileConnectionService
                .getProfileConnectionsByConnectedWithAndTypeAndIsAccepted(connectedWith, connectionType, isAccepted,
                        Integer.parseInt(page), Integer.parseInt(size));
        PaginatedResponse response = PaginatedResponse.builder()
                .currentPage(profileConnsPage.getNumber())
                .totalItems(profileConnsPage.getTotalElements())
                .totalPages(profileConnsPage.getTotalPages())
                .objects(profileConnsPage.getContent())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(path = "profile-connections", method = RequestMethod.POST)
    public ResponseEntity<?> addProfileConnection(
            @RequestBody @NonNull ProfileConnection profileConnection, Principal principal)
            throws ForbiddenException, NotFoundException, ConflictException
    {
        UUID userId = userService.findUserIdByUsername(principal.getName());
        Profile userProfile = profileService.getProfileById(userId);

        profileConnection.setProfile(userProfile);

        EConnectionType connectionType = profileConnection.getConnectionType();
        if(connectionType != EConnectionType.WITH_USER){
            Profile specialistProfile = profileService.getProfileById(profileConnection.getConnectedWith().getId());

            if(!hasConnectionTypeRole(specialistProfile, connectionType)){
                throw new ForbiddenException("The specialist does not have a corresponding role.");
            }
        }

        ProfileConnection pConnResult = profileConnectionService.addProfileConnection(profileConnection);
        return new ResponseEntity<>(pConnResult, HttpStatus.CREATED);
    }

    private boolean hasConnectionTypeRole(Profile profile, EConnectionType connectionType) {
        return connectionType == EConnectionType.WITH_DOCTOR    && hasRole(profile, ERole.ROLE_DOCTOR)
            || connectionType == EConnectionType.WITH_DIETICIAN && hasRole(profile, ERole.ROLE_DIETICIAN)
            || connectionType == EConnectionType.WITH_TRAINER   && hasRole(profile, ERole.ROLE_TRAINER);
    }

    private boolean hasRole(Profile profile, ERole role) {
        Set<Role> roles = profile.getRoles();
        for(Role r : roles){
            if(r.getRole().equals(role))
                return true;
        }
        return false;
    }

    @RequestMapping(path = "profile-connections/{id}/mark-as-accepted", method = RequestMethod.PUT)
    public ResponseEntity<ProfileConnection> markAsAccepted(@PathVariable("id") UUID id, Principal principal)
            throws NotFoundException, ForbiddenException, ConflictException
    {
        UUID userId = userService.findUserIdByUsername(principal.getName());
        ProfileConnection profileConnection = profileConnectionService.getProfileConnectionById(id);

        if(!profileConnection.getConnectedWith().getId().equals(userId)){
            throw new ForbiddenException("You do not have access rights to do that!");
        }

        ProfileConnection pConnResult = profileConnectionService.markAsAccepted(profileConnection);
        return new ResponseEntity<>(pConnResult, HttpStatus.OK);
    }

    @RequestMapping(path = "profile-connections/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteProfileConnection(@PathVariable("id") UUID id, Principal principal)
            throws NotFoundException, ForbiddenException
    {
        UUID userId = userService.findUserIdByUsername(principal.getName());
        ProfileConnection profileConnection = profileConnectionService.getProfileConnectionById(id);

        if(!profileConnection.getProfile().getId().equals(userId)
                && !profileConnection.getConnectedWith().getId().equals(userId)){
            throw new ForbiddenException("You do not have access rights to do that!");
        }

        profileConnectionService.deleteProfileConnectionById(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }








}
