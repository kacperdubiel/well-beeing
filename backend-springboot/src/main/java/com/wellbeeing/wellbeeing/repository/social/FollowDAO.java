package com.wellbeeing.wellbeeing.repository.social;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.social.Follow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("followDAO")
public interface FollowDAO extends JpaRepository<Follow, Long> {
    Page<Follow> findAllByFollower(Profile follower, Pageable pageable);
    Page<Follow> findAllByFollowed(Profile followed, Pageable pageable);
    Optional<Follow> findByFollowerAndFollowed(Profile follower, Profile followed);
}
