package com.wellbeeing.wellbeeing.service.social.postPositioning;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.social.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface PostPositioningStrategy {

    Page<Post> generatePositioning(Profile myProfile, Date requestDate, Pageable pageable);
}
