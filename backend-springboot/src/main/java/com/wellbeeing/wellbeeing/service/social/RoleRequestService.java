package com.wellbeeing.wellbeeing.service.social;

import com.wellbeeing.wellbeeing.domain.social.RoleRequest;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface RoleRequestService {

    Page<RoleRequest> getAllRoleRequests(Specification<RoleRequest> reqSpec, Pageable pageable);
    RoleRequest getRoleRequest(long roleRequestId);
    List<RoleRequest> getMyRoleRequests(String ownerName);

    RoleRequest submitRoleRequest(RoleRequest roleRequest, String submitterName) throws NotFoundException;

    RoleRequest updateRoleRequest(RoleRequest roleRequest, String updaterName) throws NotFoundException;
    boolean processRoleRequest(RoleRequest roleRequest) throws NotFoundException;
    boolean cancelRoleRequest(long roleRequestId, String cancellerName) throws NotFoundException;
}
