package com.wellbeeing.wellbeeing.api.account;

import com.wellbeeing.wellbeeing.domain.account.DoctorSpecialization;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.message.PaginatedResponse;
import com.wellbeeing.wellbeeing.service.account.DoctorSpecializationService;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import com.wellbeeing.wellbeeing.service.account.UserService;
import com.wellbeeing.wellbeeing.service.files.FileService;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "/profile")
@RestController
public class ProfileController {
    private final ProfileService profileService;
    private final UserService userService;
    private final FileService fileService;
    private final DoctorSpecializationService doctorSpecService;

    @Autowired
    public ProfileController(@Qualifier("profileService") ProfileService profileService,
                             @Qualifier("userService") UserService userService,
                             @Qualifier("fileService") FileService fileService,
                             @Qualifier("doctorSpecializationService") DoctorSpecializationService doctorSpecService) {
        this.profileService = profileService;
        this.userService = userService;
        this.fileService = fileService;
        this.doctorSpecService = doctorSpecService;
    }

    @GetMapping(path = "")
    public ResponseEntity<?> getProfilesFiltered(
            @And({
                    @Spec(path = "fullName", spec = LikeIgnoreCase.class),
                    @Spec(path = "eSportTag", spec = Equal.class),
                    @Spec(path = "eNutritionTag", spec = Equal.class),
            }) Specification<Profile> profileSpec,
            @PageableDefault(size = 8)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "lastName", direction = Sort.Direction.ASC),
                    @SortDefault(sort = "firstName", direction = Sort.Direction.ASC)
            }) Pageable pageable) {

        Page<Profile> pageProfiles = profileService.getProfilesFiltered(profileSpec, pageable);
        return new ResponseEntity<>(pageProfiles, HttpStatus.OK);
    }

    @GetMapping(path = "/my")
    public ResponseEntity<?> getProfile(Principal principal) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        Profile profile = profileService.getProfileById(profileId);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @GetMapping(path = "/{profile_id}")
    public ResponseEntity<?> getProfile(@PathVariable("profile_id") UUID profileId, Principal principal) throws NotFoundException {
        Profile profile = profileService.getProfileById(profileId);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @PatchMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> partialUpdateProfileBy(Principal principal,
                                                    @RequestBody Map<String, Object> fields) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        Profile actProfile = profileService.partialUpdateProfile(profileId, fields);
        return new ResponseEntity<>(actProfile, HttpStatus.OK);
    }

    @PostMapping("/import")
    public ResponseEntity<?> importData(MultipartFile file, Principal principal) throws NotFoundException {

        String fileName = fileService.save(file);

        UUID profileId = userService.findUserIdByUsername(principal.getName());
        Profile profile = profileService.getProfileById(profileId);
        profile.setProfileImgPath(fileName);

        Map<String, Object> fields = new HashMap<>();
        fields.put("profileImgPath", fileName);

        profileService.partialUpdateProfile(profileId, fields);

        return new ResponseEntity<>("Sent", HttpStatus.OK);
    }

    @GetMapping("/export/{profileId}")
    public ResponseEntity<?> exportData(@PathVariable UUID profileId) throws NotFoundException {
        Profile profile = profileService.getProfileById(profileId);
//        System.out.println("sciezka " + profile.getProfileImgPath());
//        System.out.println("klasa " + profile.getProfileImgPath().getClass());
        if (profile.getProfileImgPath() != null) {
            Resource file = fileService.load(profile.getProfileImgPath());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "profilePhoto")
                    .contentType(MediaType.parseMediaType("image/png"))
                    .body(file);
        }
        else
//            throw new NotFoundException("No picture to download");
            return new ResponseEntity<>("No picture to download", HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/doctors/doctor-specializations/{spec_id}")
    public ResponseEntity<?> getDoctorsProfiles(@PathVariable("spec_id") UUID specializationId,
                                                @RequestParam(value = "like", defaultValue = "") String like,
                                                @RequestParam(value = "page", defaultValue = "0") String page,
                                                @RequestParam(value = "size", defaultValue = "10") String size)
            throws NotFoundException {
        DoctorSpecialization specialization = doctorSpecService.getDoctorSpecializationById(specializationId);
        Page<Profile> doctorsPage = profileService.getDoctorsBySpecialization(specialization, like,
                Integer.parseInt(page), Integer.parseInt(size));

        PaginatedResponse response = PaginatedResponse.builder()
                .currentPage(doctorsPage.getNumber())
                .totalItems(doctorsPage.getTotalElements())
                .totalPages(doctorsPage.getTotalPages())
                .objects(doctorsPage.getContent())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/trainers")
    public ResponseEntity<?> getTrainersProfiles(
            @Join(path = "profileUser", alias = "us")
            @Join(path = "us.roles", alias = "r")
            @And({
                    @Spec(path = "fullName", spec = LikeIgnoreCase.class),
                    @Spec(path = "r.name", params = "role", spec = Equal.class)
            }) Specification<Profile> profileSpec,
            @PageableDefault(sort = {"lastName"}, size = 20) Pageable pageable, Principal principal) {
        return new ResponseEntity<>(profileService.getTrainersProfiles(profileSpec, pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/dieticians")
    public ResponseEntity<?> getDieticiansProfiles(@RequestParam(value = "like", defaultValue = "") String like,
                                                   @RequestParam(value = "page", defaultValue = "0") String page,
                                                   @RequestParam(value = "size", defaultValue = "10") String size) {
        Page<Profile> dieticians = profileService.getDieticiansProfiles(like, Integer.parseInt(page), Integer.parseInt(size));
        PaginatedResponse response = PaginatedResponse.builder()
                .currentPage(dieticians.getNumber())
                .totalItems(dieticians.getTotalElements())
                .totalPages(dieticians.getTotalPages())
                .objects(dieticians.getContent())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
