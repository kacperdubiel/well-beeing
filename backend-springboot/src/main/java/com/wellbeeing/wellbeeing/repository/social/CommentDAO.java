package com.wellbeeing.wellbeeing.repository.social;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.social.Comment;
import com.wellbeeing.wellbeeing.domain.social.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("commentDAO")
public interface CommentDAO extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByPostPostId(long postId, Pageable pageable);
}
