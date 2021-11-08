package com.wellbeeing.wellbeeing.service.social;

import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.social.Post;
import com.wellbeeing.wellbeeing.domain.social.RoleRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface PostService {
    Page<Post> getMyPosts(String ownerName, Pageable pageable);
    Post getPost(long postId);

    Post addPost(Post post, String creatorName) throws NotFoundException;
}
