package com.wellbeeing.wellbeeing.service.social;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.social.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface PostService {
    Page<Post> getUsersPosts(Profile creatorParam, Pageable pageable);
    Post getPost(long postId);
    Post findOriginalPost(Post post);

    Post addPost(Post post, String creatorName) throws NotFoundException;
    Post sharePost(long postId, Post post, String creatorName) throws NotFoundException;
    Post partialUpdatePost(long id, Map<String, Object> fields, String updaterName) throws NotFoundException, ForbiddenException;

    Post updatePost(long id, Post post, String updaterName) throws NotFoundException, ForbiddenException;
    boolean deletePost(long postId, String cancellerName) throws NotFoundException, ForbiddenException;
}
