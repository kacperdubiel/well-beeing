package com.wellbeeing.wellbeeing.repository.social;
import com.wellbeeing.wellbeeing.domain.social.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository("commentDAO")
public interface CommentDAO extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {
    Page<Comment> findAllByPostPostId(long postId, Pageable pageable);
}
