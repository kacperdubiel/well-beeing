package com.wellbeeing.wellbeeing.repository;

import com.wellbeeing.wellbeeing.domain.account.ERole;
import com.wellbeeing.wellbeeing.domain.account.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("roleDAO")
public interface RoleDAO extends JpaRepository<Role, UUID> {
    Optional<Role> findRoleByName(ERole name);
}
