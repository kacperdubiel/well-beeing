package com.wellbeeing.wellbeeing.service.social;

import com.wellbeeing.wellbeeing.domain.account.ESex;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.social.ENutritionTag;
import com.wellbeeing.wellbeeing.domain.social.EPrivacy;
import com.wellbeeing.wellbeeing.domain.social.ESportTag;
import com.wellbeeing.wellbeeing.domain.social.Post;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.repository.social.PostDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

@Service("postService")
public class PostServiceImpl implements PostService {
    private final PostDAO postDAO;
    private final UserDAO userDAO;

    public PostServiceImpl(@Qualifier("postDAO") PostDAO postDAO,
                           @Qualifier("userDAO") UserDAO userDAO) {
        this.postDAO = postDAO;
        this.userDAO = userDAO;
    }

    @Override
    public Page<Post> getMyPosts(Profile creatorParam, Pageable pageable) {
        return postDAO.findAllByCreatorProfileUserEmail(creatorParam, pageable);
    }

    @Override
    public Post getPost(long postId) {
        return postDAO.findPostByPostId(postId).orElse(null);
    }

    @Override
    public Post addPost(Post post, String creatorName) throws NotFoundException {
        User user = userDAO.findUserByEmail(creatorName).orElse(null);

        if (user == null)
        {
            throw new UsernameNotFoundException("User: " + creatorName + " not found");
        }

        post.setCreator(user.getProfile());
        postDAO.save(post);
        return post;
    }

    @Override
    public Post updatePost(long id, Post post, String updaterName) throws NotFoundException {
        post.setPostId(id);
        Post targetPost = postDAO.findPostByPostId(id).orElse(null);

        if (targetPost == null)
            throw new NotFoundException(String.format("There's no post with id=%d", id));

        Profile updaterProfile = userDAO.findUserByEmail(updaterName).orElse(null).getProfile();
        Profile postOwner = targetPost.getCreator();

        if (updaterProfile != postOwner)
            throw new NotFoundException("That is not your post!");

        postDAO.save(post);
        return post;

    }

    @Override
    public Post partialUpdatePost(long id, Map<String, Object> fields, String updaterName) throws NotFoundException {
        Post targetPost = postDAO.findPostByPostId(id).orElse(null);

        if (targetPost == null || fields == null || fields.isEmpty())
            throw new NotFoundException("Bad request!");

        Profile updaterProfile = userDAO.findUserByEmail(updaterName).orElse(null).getProfile();
        Profile postOwner = targetPost.getCreator();

        if (updaterProfile != postOwner)
            throw new NotFoundException("That is not your post!");

        fields.remove("postId");
        fields.remove("addedDate");
        fields.remove("creator");
        fields.remove("isSharing");
        fields.remove("isDeleted");
        fields.remove("sharing");
        fields.remove("deleted");
        fields.remove("originalPost");
        fields.remove("sharingPosts");
        fields.remove("comments");
        fields.remove("likes");

        fields.forEach((k, v) -> {

            Field field = ReflectionUtils.findField(Post.class, k);

            if (field != null) {
                field.setAccessible(true);
                if (field.getType() == EPrivacy.class)
                    v = EPrivacy.valueOf((String) v);
                ReflectionUtils.setField(field, targetPost, v);
            }
        });

        postDAO.save(targetPost);
        return targetPost;

    }

    @Override
    public boolean deletePost(long postId, String cancellerName) throws NotFoundException {
        Post targetPost = postDAO.findPostByPostId(postId).orElse(null);

        if (targetPost == null)
            throw new NotFoundException(String.format("There's no post with id=%d", postId));

        Profile cancellerProfile = userDAO.findUserByEmail(cancellerName).orElse(null).getProfile();
        Profile postOwner = targetPost.getCreator();

        if (cancellerProfile != postOwner)
            throw new NotFoundException("That is not your post!");

        targetPost.setDeleted(true);
        postDAO.save(targetPost);
        return true;
    }
}
