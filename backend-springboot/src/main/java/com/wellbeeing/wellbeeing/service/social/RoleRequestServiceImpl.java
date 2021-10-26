package com.wellbeeing.wellbeeing.service.social;

import com.wellbeeing.wellbeeing.domain.social.RoleRequest;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class RoleRequestServiceImpl implements RoleRequestService {

    @Override
    public Page<RoleRequest> getAllRoleRequests(Pageable pageable) {
        return null;
    }

    @Override
    public RoleRequest getRoleRequest(long roleRequestId) {
        return null;
    }

    @Override
    public RoleRequest updateRoleRequest(RoleRequest roleRequest) throws NotFoundException {
        return null;
    }

    @Override
    public RoleRequest processRoleRequest(RoleRequest roleRequest) {
        return null;
    }
}
