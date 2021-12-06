package com.wellbeeing.wellbeeing.service.social;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.domain.diet.nutrition_plan.NutritionPlan;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.social.Post;
import com.wellbeeing.wellbeeing.domain.sport.TrainingPlan;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.repository.diet.nutrition_plan.NutritionPlanDAO;
import com.wellbeeing.wellbeeing.repository.social.CommentDAO;
import com.wellbeeing.wellbeeing.repository.social.LikeDAO;
import com.wellbeeing.wellbeeing.repository.social.PostDAO;
import com.wellbeeing.wellbeeing.repository.sport.TrainingPlanDAO;
import com.wellbeeing.wellbeeing.service.social.postPositioning.PostPositioningPointsStrategy;
import com.wellbeeing.wellbeeing.service.social.postPositioning.PostPositioningStrategy;
import com.wellbeeing.wellbeeing.service.sport.alg.TrainingPlanGeneratorRandomStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service("postService")
public class PostServiceImpl implements PostService {
    private final PostDAO postDAO;
    private final UserDAO userDAO;
    private final LikeDAO likeDAO;
    private final CommentDAO commentDAO;
    private final NutritionPlanDAO nutritionPlanDAO;
    private final TrainingPlanDAO trainingPlanDAO;

    public PostServiceImpl(@Qualifier("postDAO") PostDAO postDAO,
                           @Qualifier("userDAO") UserDAO userDAO,
                           @Qualifier("likeDAO") LikeDAO likeDAO,
                           @Qualifier("commentDAO") CommentDAO commentDAO,
                           @Qualifier("nutritionPlanDAO") NutritionPlanDAO nutritionPlanDAO,
                           @Qualifier("trainingPlanDAO") TrainingPlanDAO trainingPlanDAO) {
        this.postDAO = postDAO;
        this.userDAO = userDAO;
        this.likeDAO = likeDAO;
        this.commentDAO = commentDAO;
        this.nutritionPlanDAO = nutritionPlanDAO;
        this.trainingPlanDAO = trainingPlanDAO;
    }

    @Override
    public Page<Post> getUsersPosts(Profile creatorParam, Pageable pageable) {
        return postDAO.findAllByCreator(creatorParam, pageable);
    }

    @Override
    public Post getPost(long postId) {
        return postDAO.findPostByPostId(postId).orElse(null);
    }

    @Override
    public Post findOriginalPost(Post post) {
        if (post.isSharing() && post.getOriginalPost() != null && !post.getOriginalPost().isDeleted())
            return findOriginalPost(post.getOriginalPost());
        else
            return post;
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
    public Post sharePost(long postId, Post post, String creatorName) throws NotFoundException {
        User user = userDAO.findUserByEmail(creatorName).orElse(null);

        if (user == null)
        {
            throw new UsernameNotFoundException("User: " + creatorName + " not found");
        }

        Post sharedPost = postDAO.findPostByPostId(postId).orElse(null);

        if (sharedPost == null || sharedPost.isDeleted())
            throw new NotFoundException(String.format("There's no post with id=%d", postId));

        sharedPost = findOriginalPost(sharedPost);

        Post sharingPost = new Post(post.getPostContent(), user.getProfile(), sharedPost);

        sharedPost.increaseSharingCounter();

        postDAO.save(sharedPost);
        postDAO.save(sharingPost);
        return sharingPost;
    }

    @Override
    public Post shareNutritionPlan(UUID nutritionPlanId, Post post, String creatorName) throws NotFoundException {
        User user = userDAO.findUserByEmail(creatorName).orElse(null);

        if (user == null)
        {
            throw new UsernameNotFoundException("User: " + creatorName + " not found");
        }

        NutritionPlan sharedPlan = nutritionPlanDAO.findById(nutritionPlanId).orElse(null);

        if (sharedPlan == null)
            throw new NotFoundException(String.format("There's no nutrition plan with id=%s", nutritionPlanId));

        Post sharingPost = new Post(post.getPostContent(), user.getProfile(), sharedPlan);

        nutritionPlanDAO.save(sharedPlan);
        postDAO.save(sharingPost);
        return sharingPost;
    }

    @Override
    public Post shareTrainingPlan(long trainingPlanId, Post post, String creatorName) throws NotFoundException {
        User user = userDAO.findUserByEmail(creatorName).orElse(null);

        if (user == null)
        {
            throw new UsernameNotFoundException("User: " + creatorName + " not found");
        }

        TrainingPlan sharedPlan = trainingPlanDAO.findById(trainingPlanId).orElse(null);

        if (sharedPlan == null)
            throw new NotFoundException(String.format("There's no training plan with id=%s", trainingPlanId));

        Post sharingPost = new Post(post.getPostContent(), user.getProfile(), sharedPlan);

        trainingPlanDAO.save(sharedPlan);
        postDAO.save(sharingPost);
        return sharingPost;
    }

    @Override
    public Post updatePost(long id, Post post, String updaterName) throws NotFoundException, ForbiddenException {
        post.setPostId(id);
        Post targetPost = postDAO.findPostByPostId(id).orElse(null);

        if (targetPost == null || targetPost.isDeleted())
            throw new NotFoundException(String.format("There's no post with id=%d", id));

        Profile updaterProfile = userDAO.findUserByEmail(updaterName).orElse(null).getProfile();
        Profile postOwner = targetPost.getCreator();

        if (updaterProfile != postOwner)
            throw new ForbiddenException("That is not your post!");

        postDAO.save(post);
        return post;

    }

    @Override
    public Post partialUpdatePost(long id, Map<String, Object> fields, String updaterName) throws NotFoundException, ForbiddenException {
        Post targetPost = postDAO.findPostByPostId(id).orElse(null);

        if (targetPost == null || targetPost.isDeleted() || fields == null || fields.isEmpty())
            throw new NotFoundException(String.format("There's no post with id=%d", id));

        Profile updaterProfile = userDAO.findUserByEmail(updaterName).orElse(null).getProfile();
        Profile postOwner = targetPost.getCreator();

        if (updaterProfile != postOwner)
            throw new ForbiddenException("That is not your post!");

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
                ReflectionUtils.setField(field, targetPost, v);
            }
        });

        postDAO.save(targetPost);
        return targetPost;

    }

    @Override
    public boolean deletePost(long postId, String cancellerName) throws NotFoundException, ForbiddenException {
        Post targetPost = postDAO.findPostByPostId(postId).orElse(null);

        if (targetPost == null || targetPost.isDeleted())
            throw new NotFoundException(String.format("There's no post with id=%d", postId));

        Profile cancellerProfile = userDAO.findUserByEmail(cancellerName).orElse(null).getProfile();
        Profile postOwner = targetPost.getCreator();

        if (cancellerProfile != postOwner)
            throw new ForbiddenException("That is not your post!");

        targetPost.setDeleted(true);
        postDAO.save(targetPost);
        return true;
    }

    @Override
    public Page<Post> getPostsFeed(String userName, Date requestDate, Pageable pageable, String positioningType) {
        Profile profile = userDAO.findUserByEmail(userName).orElse(null).getProfile();
        PostPositioningStrategy postPositioningStrategy;

        switch (positioningType) {
            case "points":
                postPositioningStrategy = new PostPositioningPointsStrategy(likeDAO, commentDAO);
                break;
            default:
                postPositioningStrategy = new PostPositioningPointsStrategy(likeDAO, commentDAO);
                break;
        }

        return postPositioningStrategy.generatePositioning(profile, requestDate, pageable);

    }
}
