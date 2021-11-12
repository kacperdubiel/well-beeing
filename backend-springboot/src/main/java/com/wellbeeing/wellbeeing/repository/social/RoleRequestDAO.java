package com.wellbeeing.wellbeeing.repository.social;

import com.wellbeeing.wellbeeing.domain.account.ERole;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.account.Role;
import com.wellbeeing.wellbeeing.domain.social.EStatus;
import com.wellbeeing.wellbeeing.domain.social.Post;
import com.wellbeeing.wellbeeing.domain.social.RoleRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository("roleRequestDAO")
public interface RoleRequestDAO extends JpaRepository<RoleRequest, Long>, JpaSpecificationExecutor<RoleRequest> {
//    Page<RoleRequest> findAllBySubmitterId(UUID submitter_id, Pageable pageable);
    List<RoleRequest> findRoleRequestsBySubmitterProfileUserEmailOrderByAddedDateDesc(String userName);
    List<RoleRequest> findRoleRequestsBySubmitterProfileUserEmailAndRoleAndStatus(String userName, ERole role, EStatus status);

}
