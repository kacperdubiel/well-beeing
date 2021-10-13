package com.wellbeeing.wellbeeing.repository;

import com.wellbeeing.wellbeeing.domain.ERole;
import com.wellbeeing.wellbeeing.domain.Role;
import com.wellbeeing.wellbeeing.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository("roleDAO")
public interface RoleDAO extends JpaRepository<Role, UUID> {
    Optional<Role> findRoleByName(ERole name);
}
