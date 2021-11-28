package com.wellbeeing.wellbeeing.service.social;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.social.Follow;
import com.wellbeeing.wellbeeing.repository.social.FollowDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FollowService {
    Page<Follow> getFollowsByFollower(Profile follower, Pageable pageable);
    Page<Follow> getFollowsByFollowed(Profile followed, Pageable pageable);
    Follow addFollow(Profile newFollowed, String followerName) throws ForbiddenException;
    boolean deleteFollow(Profile followed, String followerName) throws NotFoundException;

    boolean findFollow(Profile followed, String followerName);
}
