package com.wellbeeing.wellbeeing.repository.account;

import com.wellbeeing.wellbeeing.domain.account.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("userDAO")
public interface UserDAO extends JpaRepository<User, UUID>{
    Optional<User> findUserByEmail(String email);
}
