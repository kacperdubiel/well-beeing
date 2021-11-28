package com.wellbeeing.wellbeeing.service.social;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.social.Follow;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.repository.social.FollowDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("followService")
public class FollowServiceImpl implements FollowService {

    private final UserDAO userDAO;
    private final FollowDAO followDAO;

    public FollowServiceImpl(@Qualifier("userDAO") UserDAO userDAO,
                             @Qualifier("followDAO") FollowDAO followDAO) {
        this.userDAO = userDAO;
        this.followDAO = followDAO;
    }

    @Override
    public Page<Follow> getFollowsByFollower(Profile follower, Pageable pageable) {
        return followDAO.findAllByFollower(follower, pageable);
    }

    @Override
    public Page<Follow> getFollowsByFollowed(Profile followed, Pageable pageable) {
        return followDAO.findAllByFollowed(followed, pageable);
    }

    @Override
    public Follow addFollow(Profile newFollowed, String followerName) throws ForbiddenException {
        User user = userDAO.findUserByEmail(followerName).orElse(null);

        if (user == null)
            throw new UsernameNotFoundException("User: " + followerName + " not found");

        if (user.getProfile() == newFollowed)
            throw new ForbiddenException("You can not follow yourself!");

        Follow existingFollow = followDAO.findByFollowerAndFollowed(user.getProfile(), newFollowed).orElse(null);
        if (existingFollow != null)
            throw new ForbiddenException("You already follow this user!");

        Follow newFollow = new Follow(user.getProfile(), newFollowed);

        followDAO.save(newFollow);
        return newFollow;
    }

    @Override
    public boolean deleteFollow(Profile followed, String followerName) throws NotFoundException {
        User user = userDAO.findUserByEmail(followerName).orElse(null);

        if (user == null)
            throw new UsernameNotFoundException("User: " + followerName + " not found");

        Follow followToDelete = followDAO.findByFollowerAndFollowed(user.getProfile(), followed).orElse(null);
        if (followToDelete == null)
            throw new NotFoundException("Follow not found");

        followDAO.delete(followToDelete);
        return true;
    }
}
