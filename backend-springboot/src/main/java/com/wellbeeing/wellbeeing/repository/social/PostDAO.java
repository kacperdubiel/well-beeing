package com.wellbeeing.wellbeeing.repository.social;

import com.wellbeeing.wellbeeing.domain.social.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("postDAO")
public interface PostDAO extends JpaRepository<Post, Long> {
    List<Post> findAllByCreator_Id(UUID creator_id);
}
