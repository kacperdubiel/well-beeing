package com.wellbeeing.wellbeeing.repository.social;

import com.wellbeeing.wellbeeing.domain.social.Like;
import com.wellbeeing.wellbeeing.domain.social.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("likeDAO")
public interface LikeDAO extends JpaRepository<Like, Long> {
    List<Like> findAllByPost(Post likedPost);
}
