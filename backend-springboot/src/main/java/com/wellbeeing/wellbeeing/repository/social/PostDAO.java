package com.wellbeeing.wellbeeing.repository.social;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.social.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postDAO")
public interface PostDAO extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.creator = :creatorParam AND p.isDeleted = false")
    Page<Post> findAllByCreatorProfileUserEmail(Profile creatorParam, Pageable pageable);
    Optional<Post> findPostByPostId(long postId);
    List<Post> findPostByOriginalPostPostId(long postId);
}
