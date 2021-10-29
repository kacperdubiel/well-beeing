package com.wellbeeing.wellbeeing.api.social;

import com.wellbeeing.wellbeeing.domain.account.ERole;
import com.wellbeeing.wellbeeing.domain.account.Role;
import com.wellbeeing.wellbeeing.domain.message.sport.AddTrainingPlanWithOwnerRequest;
import com.wellbeeing.wellbeeing.domain.social.RoleRequest;
import com.wellbeeing.wellbeeing.domain.sport.Exercise;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.service.social.RoleRequestService;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "/role-request")
@RestController
public class RoleRequestController {
    @Autowired
    private RoleRequestService roleRequestService;
    private UserDAO userDAO;

    public RoleRequestController(@Qualifier("roleRequestService") RoleRequestService roleRequestService) {
        this.roleRequestService = roleRequestService;
    }

    @GetMapping(path = "")
//    @RolesAllowed(ERole.Name.ROLE_ADMIN)
    public ResponseEntity<?> getRoleRequestsPaginated(@RequestParam(value = "page", defaultValue = "0") int page,
                                                   @RequestParam(value = "size", defaultValue = "3") int size) {
        List<RoleRequest> roleRequests;
        Pageable paging = PageRequest.of(page, size);

        Page<RoleRequest> pageRoleRequests;
        pageRoleRequests = roleRequestService.getAllRoleRequests(paging);
        roleRequests = pageRoleRequests.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("exercises", roleRequests);
        response.put("currentPage", pageRoleRequests.getNumber());
        response.put("totalItems", pageRoleRequests.getTotalElements());
        response.put("totalPages", pageRoleRequests.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
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
    public ResponseEntity<?> updateRoleRequest(@PathVariable(value = "id") Long roleRequestId, @RequestBody @NonNull RoleRequest roleRequest) throws NotFoundException {
        roleRequest.setRoleReqId(roleRequestId);
        roleRequestService.updateRoleRequest(roleRequest);
        return new ResponseEntity<>(roleRequest, HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}/cancel")
    public ResponseEntity<?> updateRoleRequest(@PathVariable(value = "id") Long roleRequestId) throws NotFoundException {
        roleRequestService.cancelRoleRequest(roleRequestId);
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
}
