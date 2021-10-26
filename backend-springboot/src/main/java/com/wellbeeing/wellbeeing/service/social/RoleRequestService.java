package com.wellbeeing.wellbeeing.service.social;

import com.wellbeeing.wellbeeing.domain.social.RoleRequest;
import com.wellbeeing.wellbeeing.domain.sport.Exercise;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleRequestService {

    Page<RoleRequest> getAllRoleRequests(Pageable pageable);
    RoleRequest getRoleRequest(long roleRequestId);

    RoleRequest updateRoleRequest(RoleRequest roleRequest) throws NotFoundException;
    RoleRequest processRoleRequest(RoleRequest roleRequest);
}
