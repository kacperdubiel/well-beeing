package com.wellbeeing.wellbeeing.repository.social;

import com.wellbeeing.wellbeeing.domain.social.Comment;
import com.wellbeeing.wellbeeing.domain.social.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("commentDAO")
public interface CommentDAO extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post likedPost);
}
