package com.wellbeeing.wellbeeing.service.social;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.social.Comment;
import com.wellbeeing.wellbeeing.domain.social.Like;
import com.wellbeeing.wellbeeing.domain.social.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    Comment getComment(long commentId);
    Page<Comment> getCommentsByPostId(long postId, Pageable pageable);
    Comment addComment(long postId, Comment comment, String commenterName) throws NotFoundException;
    Comment updateComment(long commentId, Comment comment, String updaterName) throws NotFoundException;
    boolean deleteComment(long commentId, String cancellerName) throws NotFoundException;
}
