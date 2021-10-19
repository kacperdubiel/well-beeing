package com.wellbeeing.wellbeeing.api.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import com.wellbeeing.wellbeeing.domain.telemedic.ProfileConnection;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import com.wellbeeing.wellbeeing.service.telemedic.ProfileConnectionService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ProfileConnectionController {
    private ProfileConnectionService profileConnectionService;
    private ProfileService profileService;

    public ProfileConnectionController(
            @Qualifier("profileConnectionService") ProfileConnectionService profileConnectionService,
            @Qualifier("profileService") ProfileService profileService){
        this.profileConnectionService = profileConnectionService;
        this.profileService = profileService;
    }

    @RequestMapping(path = "profile-connections/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProfileConnection> getProfileConnectionById(@PathVariable("id") UUID id){
        ProfileConnection pConnectionResult = profileConnectionService.getProfileConnectionById(id);
        if(pConnectionResult == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(pConnectionResult, HttpStatus.OK);
    }

    @RequestMapping(path = "profile-connections/profile/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<ProfileConnection>> getProfileConnectionsByProfile(@PathVariable("id") UUID profileId){
        try {
            Profile pConnectionsOwner = profileService.getProfileById(profileId);
            List<ProfileConnection> pConnectionsResult = profileConnectionService.getProfileConnectionsByProfile(pConnectionsOwner);

            if(pConnectionsResult == null)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            else {
                return new ResponseEntity<>(pConnectionsResult, HttpStatus.OK);
            }
        } catch (NotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "profile-connections/profile/{id}/type/{type}", method = RequestMethod.GET)
    public ResponseEntity<List<ProfileConnection>> getProfileConnectionsByProfileAndType(
            @PathVariable("id") UUID profileId, @PathVariable("type") String connectionTypeText
    ){
        try {
            Profile pConnectionsOwner = profileService.getProfileById(profileId);
            EConnectionType connectionType = EConnectionType.valueOf(connectionTypeText);

            List<ProfileConnection> pConnResult = profileConnectionService
                    .getProfileConnectionsByProfileAndType(pConnectionsOwner, connectionType);
            if(pConnResult == null)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            else {
                return new ResponseEntity<>(pConnResult, HttpStatus.OK);
            }
        } catch (NotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(path = "profile-connections", method = RequestMethod.POST)
    public ResponseEntity<ProfileConnection> addProfileConnection(@RequestBody @NonNull ProfileConnection profileConnection){
        ProfileConnection pConnResult = profileConnectionService.addProfileConnection(profileConnection);
        return new ResponseEntity<>(pConnResult, HttpStatus.CREATED);
    }

    @RequestMapping(path = "profile-connections", method = RequestMethod.PUT)
    public ResponseEntity<ProfileConnection> updateProfileConnection(@RequestBody @NonNull ProfileConnection profileConnection){
        ProfileConnection pConnResult = profileConnectionService.updateProfileConnection(profileConnection);
        if(pConnResult != null)
            return new ResponseEntity<>(pConnResult, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "profile-connections/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteProfileConnection(@PathVariable("id") UUID id){
        boolean deleteResult = profileConnectionService.deleteProfileConnectionById(id);
        if(deleteResult)
            return new ResponseEntity<>(true, HttpStatus.OK);
        else
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }








}
