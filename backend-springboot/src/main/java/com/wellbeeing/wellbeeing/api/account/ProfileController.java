package com.wellbeeing.wellbeeing.api.account;

import com.wellbeeing.wellbeeing.domain.account.DoctorProfile;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.message.ErrorMessage;
import com.wellbeeing.wellbeeing.domain.message.PaginatedResponse;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import com.wellbeeing.wellbeeing.service.account.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ProfileController {
    private ProfileService profileService;
    private UserService userService;

    @Autowired
    public ProfileController(@Qualifier("profileService") ProfileService profileService,
                             @Qualifier("userService") UserService userService){
        this.profileService = profileService;
        this.userService = userService;
    }

    /*@RequestMapping(path = "/profile/{profileId}", method = RequestMethod.GET)
    public ResponseEntity<?> getProfileById(@PathVariable("profileId") UUID profileId){
        try {
            Profile profile = profileService.getProfileById(profileId);
            return new ResponseEntity<>(profile, HttpStatus.OK);
        }
        catch (NotFoundException e){
            return new ResponseEntity<>(new ErrorMessage("Not found: " + e.getMessage(),
                    "404"), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ErrorMessage("Server error: " + e.getMessage(),
                    "500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @RequestMapping(path = "/profile", method = RequestMethod.GET)
    public ResponseEntity<?> getProfile(Principal principal){
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        try {
            Profile profile = profileService.getProfileById(profileId);
            return new ResponseEntity<>(profile, HttpStatus.OK);
        }
        catch (NotFoundException e){
            return new ResponseEntity<>(new ErrorMessage("Not found: " + e.getMessage(),
                    "404"), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ErrorMessage("Server error: " + e.getMessage(),
                    "500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/profile", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProfileById(Principal principal,
                                               @NonNull @RequestBody Profile profile){
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        try {
            Profile actProfile = profileService.updateProfile(profile, profileId);
            return new ResponseEntity<>(actProfile, HttpStatus.OK);
        }
        catch (NotFoundException e){
            return new ResponseEntity<>(new ErrorMessage("Not found: " + e.getMessage(),
                    "404"), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ErrorMessage("Server error: " + e.getMessage(),
                    "500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/doctors")
    public ResponseEntity<?> getDoctorsProfiles(@RequestParam(value = "page", defaultValue = "0") String page,
                                                @RequestParam(value = "size", defaultValue = "10") String size) {

        Page<DoctorProfile> doctorsPage = profileService.getDoctorsProfiles(Integer.parseInt(page), Integer.parseInt(size));
        PaginatedResponse response = PaginatedResponse.builder()
                .currentPage(doctorsPage.getNumber())
                .totalItems(doctorsPage.getTotalElements())
                .totalPages(doctorsPage.getTotalPages())
                .objects(doctorsPage.getContent())
                .build();


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/trainers")
    public ResponseEntity<?> getTrainersProfiles() {
        return new ResponseEntity<>(profileService.getTrainersProfiles(), HttpStatus.OK);
    }
}
