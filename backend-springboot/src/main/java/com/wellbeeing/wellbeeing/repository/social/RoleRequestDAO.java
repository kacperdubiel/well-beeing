package com.wellbeeing.wellbeeing.repository.social;

import com.wellbeeing.wellbeeing.domain.social.Post;
import com.wellbeeing.wellbeeing.domain.social.RoleRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository("roleRequestDAO")
public interface RoleRequestDAO extends JpaRepository<RoleRequest, Long> {
    Page<RoleRequest> getAllByAddedDate(Date addedDate, Pageable pageable);
}
