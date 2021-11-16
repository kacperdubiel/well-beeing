package com.wellbeeing.wellbeeing.service.social;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.social.Like;
import com.wellbeeing.wellbeeing.domain.social.Post;

public interface LikeService {

    Like addLike(Post post, Profile liker);
    boolean deleteLike(Like like);
    boolean reactToPost(long postId, String likerName) throws NotFoundException;
}
