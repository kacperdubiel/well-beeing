package com.wellbeeing.wellbeeing.api.social;

import com.wellbeeing.wellbeeing.domain.account.ERole;
import com.wellbeeing.wellbeeing.domain.social.RoleRequest;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.service.files.FileService;
import com.wellbeeing.wellbeeing.service.social.RoleRequestService;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "/role-request")
@RestController
public class RoleRequestController {
    @Autowired
    private RoleRequestService roleRequestService;
    private UserDAO userDAO;
    private final FileService fileService;

    public RoleRequestController(@Qualifier("roleRequestService") RoleRequestService roleRequestService, @Qualifier("fileService") FileService fileService) {
        this.roleRequestService = roleRequestService;
        this.fileService = fileService;
    }
    @GetMapping(path = "")
    @RolesAllowed(ERole.Name.ROLE_ADMIN)
    public ResponseEntity<?> getRoleRequestsFiltered(
            @And({
            @Spec(path = "status", spec = Equal.class),
            @Spec(path = "role", spec = Equal.class),
             }) Specification<RoleRequest> reqSpec,
            @PageableDefault(sort = {"addedDate"}, size = 20, direction = Sort.Direction.DESC) Pageable pageable, Principal principal) {

        Page<RoleRequest> pageRoleRequests = roleRequestService.getAllRoleRequests(reqSpec, pageable);
        return new ResponseEntity<>(pageRoleRequests, HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<?> getRoleRequestId(@PathVariable(value = "id") Long roleRequestId) {
        return new ResponseEntity<>(roleRequestService.getRoleRequest(roleRequestId), HttpStatus.OK);
    }

    @GetMapping(path = "/my")
    public ResponseEntity<?> getMyRoleRequests(Principal principal) {
        return new ResponseEntity<>(roleRequestService.getMyRoleRequests(principal.getName()), HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<?> submitRoleRequest(@RequestBody @NonNull RoleRequest roleRequest, Principal principal) throws NotFoundException {
        RoleRequest createdRoleRequest = roleRequestService.submitRoleRequest(roleRequest, principal.getName());
        return new ResponseEntity<>(createdRoleRequest, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateRoleRequest(@PathVariable(value = "id") Long roleRequestId, @RequestBody @NonNull RoleRequest roleRequest, Principal principal) throws NotFoundException {
        roleRequest.setRoleReqId(roleRequestId);
        roleRequestService.updateRoleRequest(roleRequest, principal.getName());
        return new ResponseEntity<>(roleRequest, HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}/cancel")
    public ResponseEntity<?> cancelRoleRequest(@PathVariable(value = "id") Long roleRequestId, Principal principal) throws NotFoundException {
        roleRequestService.cancelRoleRequest(roleRequestId, principal.getName());
        RoleRequest cancelledRoleRequest = roleRequestService.getRoleRequest(roleRequestId);
        return new ResponseEntity<>(cancelledRoleRequest, HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}/process")
    public ResponseEntity<?> processRoleRequest(@PathVariable(value = "id") Long roleRequestId, @RequestBody @NonNull RoleRequest roleRequest) throws NotFoundException {
        roleRequest.setRoleReqId(roleRequestId);
        roleRequestService.processRoleRequest(roleRequest);
        RoleRequest processedRoleRequest = roleRequestService.getRoleRequest(roleRequestId);
        return new ResponseEntity<>(processedRoleRequest, HttpStatus.OK);
    }

    SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
    @PostMapping("/import/{requestId}")
    public ResponseEntity<?> importData(MultipartFile file, @PathVariable long requestId, Principal principal) throws NotFoundException {

        String fileName = fileService.save(file);

        RoleRequest roleRequest = roleRequestService.getRoleRequest(requestId);
        roleRequest.setDocumentImgPath(fileName);
        roleRequestService.updateRoleRequest(roleRequest, principal.getName());

        return new ResponseEntity<>("Sent", HttpStatus.OK);
    }

    @GetMapping("/export/{requestId}")
    public ResponseEntity<?> exportData(@PathVariable long requestId) {
        RoleRequest roleRequest = roleRequestService.getRoleRequest(requestId);

        Resource file = fileService.load(roleRequest.getDocumentImgPath());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "request")
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(file);
    }
}
