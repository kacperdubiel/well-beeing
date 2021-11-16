package com.wellbeeing.wellbeeing.service.social;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.social.Like;
import com.wellbeeing.wellbeeing.domain.social.Post;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.repository.social.LikeDAO;
import com.wellbeeing.wellbeeing.repository.social.PostDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("likeService")
public class LikeServiceImpl implements LikeService {

    private final PostDAO postDAO;
    private final UserDAO userDAO;
    private final LikeDAO likeDAO;

    public LikeServiceImpl(@Qualifier("postDAO") PostDAO postDAO,
                           @Qualifier("userDAO") UserDAO userDAO,
                           @Qualifier("likeDAO") LikeDAO likeDAO) {
        this.postDAO = postDAO;
        this.userDAO = userDAO;
        this.likeDAO = likeDAO;
    }

    @Override
    public Like addLike(Post post, Profile liker) {

        Like newLike = new Like(post, liker);
        post.addLikeToPost(newLike);

        likeDAO.save(newLike);
        postDAO.save(post);

        return newLike;

    }

    @Override
    public boolean deleteLike(Like like) {
        likeDAO.delete(like);
        return true;
    }

    @Override
    public boolean reactToPost(long postId, String likerName) throws NotFoundException {
        User user = userDAO.findUserByEmail(likerName).orElse(null);
        if (user == null)
            throw new UsernameNotFoundException("User: " + likerName + " not found");

        Post post = postDAO.findPostByPostId(postId).orElse(null);
        if (post == null)
            throw new NotFoundException(String.format("There's no post with id=%d", postId));

        Like like = likeDAO.findByPostPostIdAndLikerProfileUserEmail(postId, likerName).orElse(null);
        if (like == null)
            addLike(post, user.getProfile());
        else
            deleteLike(like);

        return true;

    }
}
