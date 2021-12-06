package com.wellbeeing.wellbeeing.repository.social;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.social.Like;
import com.wellbeeing.wellbeeing.domain.social.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("likeDAO")
public interface LikeDAO extends JpaRepository<Like, Long> {
    List<Like> findAllByPost_PostId(long post_id);
    Optional<Like> findByPostPostIdAndLikerProfileUserEmail(long postId, String userEmail);
    Optional<Like> findByLikeId(long likeId);
    List<Like> findByLikerAndPostCreator(Profile liker, Profile postCreator);
}
