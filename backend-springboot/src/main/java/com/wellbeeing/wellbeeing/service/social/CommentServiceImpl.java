package com.wellbeeing.wellbeeing.service.social;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.social.Comment;
import com.wellbeeing.wellbeeing.domain.social.Post;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.repository.social.CommentDAO;
import com.wellbeeing.wellbeeing.repository.social.PostDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

    private final PostDAO postDAO;
    private final UserDAO userDAO;
    private final CommentDAO commentDAO;

    public CommentServiceImpl(@Qualifier("postDAO") PostDAO postDAO,
                           @Qualifier("userDAO") UserDAO userDAO,
                           @Qualifier("commentDAO") CommentDAO commentDAO) {
        this.postDAO = postDAO;
        this.userDAO = userDAO;
        this.commentDAO = commentDAO;
    }

    @Override
    public Comment getComment(long commentId) {
        return commentDAO.findById(commentId).orElse(null);
    }

    @Override
    public Page<Comment> getCommentsByPostId(Specification<Comment> comSpec, Pageable pageable) {
        return commentDAO.findAll(comSpec, pageable);
    }

    @Override
    public Comment addComment(long postId, Comment comment, String commenterName) throws NotFoundException {
        User user = userDAO.findUserByEmail(commenterName).orElse(null);
        if (user == null)
            throw new UsernameNotFoundException("Commenter: " + commenterName + " not found");

        Post post = postDAO.findPostByPostId(postId).orElse(null);
        if (post == null || post.isDeleted())
            throw new NotFoundException(String.format("There's no post with id=%d", postId));

        comment.setCommenter(user.getProfile());
        comment.setPost(post);
        commentDAO.save(comment);
        postDAO.save(post);
        return comment;
    }

    @Override
    public Comment updateComment(long commentId, Comment comment, String updaterName) throws NotFoundException, ForbiddenException {
        comment.setCommentId(commentId);
        Comment targetComment = commentDAO.findById(commentId).orElse(null);

        if (targetComment == null || targetComment.isDeleted())
            throw new NotFoundException(String.format("There's no comment with id=%d", commentId));

        Profile updaterProfile = userDAO.findUserByEmail(updaterName).orElse(null).getProfile();
        Profile commentOwner = targetComment.getCommenter();

        if (updaterProfile != commentOwner)
            throw new ForbiddenException("That is not your comment!");

        comment.setCommenter(commentOwner);
        comment.setAddedDate(targetComment.getAddedDate());
        comment.setDeleted(false);
        comment.setPost(targetComment.getPost());
        commentDAO.save(comment);
        return comment;
    }

    @Override
    public boolean deleteComment(long commentId, String cancellerName) throws NotFoundException, ForbiddenException {
        Comment targetComment = commentDAO.findById(commentId).orElse(null);

        if (targetComment == null)
            throw new NotFoundException(String.format("There's no comment with id=%d", commentId));

        Profile cancellerProfile = userDAO.findUserByEmail(cancellerName).orElse(null).getProfile();
        Profile commentOwner = targetComment.getCommenter();

        if (cancellerProfile != commentOwner)
            throw new ForbiddenException("That is not your comment!");

        targetComment.setDeleted(true);
        commentDAO.save(targetComment);
        return true;
    }
}
