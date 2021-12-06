package com.wellbeeing.wellbeeing.service.social.postPositioning;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.social.Comment;
import com.wellbeeing.wellbeeing.domain.social.Follow;
import com.wellbeeing.wellbeeing.domain.social.Like;
import com.wellbeeing.wellbeeing.domain.social.Post;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import com.wellbeeing.wellbeeing.domain.telemedic.ProfileConnection;
import com.wellbeeing.wellbeeing.repository.social.CommentDAO;
import com.wellbeeing.wellbeeing.repository.social.LikeDAO;
import com.wellbeeing.wellbeeing.repository.sport.TrainingDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PostPositioningPointsStrategy implements PostPositioningStrategy {

    private final int DAYS_BACK = 30;
    private final int MY_LIKES_WEIGHT = 3;
    private final double MY_COMMENTS_WEIGHT = 2;
    private final double LIKES_WEIGHT = 0.1;
    private final double ADDED_DATE_WEIGHT = 5;
    LikeDAO likeDAO;
    CommentDAO commentDAO;

    public PostPositioningPointsStrategy(@Qualifier("likeDAO") LikeDAO likeDAO,
                                         @Qualifier("commentDAO") CommentDAO commentDAO) {
        this.likeDAO = likeDAO;
        this.commentDAO = commentDAO;
    }

    @Override
    public Page<Post> generatePositioning(Profile myProfile, Date requestDate, Pageable pageable) {
        List<ProfileConnection> myFriendsConnections = myProfile.getProfileConnections().stream().filter(pc -> (pc.isAccepted() && pc.getConnectionType().equals(EConnectionType.WITH_USER))).collect(Collectors.toList());
        List<Profile> myFriends = new ArrayList<>();
        for (ProfileConnection connection: myFriendsConnections) {
            if(connection.getProfile().equals(myProfile))
                myFriends.add(connection.getConnectedWith());
            else
                myFriends.add(connection.getProfile());
        }
        List<Profile> myFollowed = myProfile.getFollowings().stream().map(Follow::getFollowed).collect(Collectors.toList());
        List<Profile> connectedProfiles = new ArrayList<>(myFollowed);
        connectedProfiles.removeAll(myFriends);
        connectedProfiles.addAll(myFriends);
        Map<Profile, List<Like>> likesMap = new HashMap<>();
        Map<Profile, List<Comment>> commentsMap = new HashMap<>();

        LocalDate date = LocalDate.now().minusDays(DAYS_BACK);
        Date fromDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());;
        List<Post> allPostsToShow = new ArrayList<>();
        for (Profile profile: connectedProfiles) {
            allPostsToShow.addAll(profile.getProfilePosts().stream().filter(p -> (p.getAddedDate().after(fromDate) && p.getAddedDate().before(requestDate))).collect(Collectors.toList()));
            likesMap.put(profile, likeDAO.findByLikerAndPostCreator(myProfile, profile));
            commentsMap.put(profile, commentDAO.findByCommenterAndPostCreator(myProfile, profile));
        }
        Map<Post, Double> postsToShowScores = new HashMap<>();
        for (Post post: allPostsToShow) {
            double myLikesScore = likesMap.get(post.getCreator()).stream().mapToDouble(l ->
                    1f/(ChronoUnit.DAYS.between(l.getAddedDate().toInstant(), new Date().toInstant())+1)).sum() * MY_LIKES_WEIGHT;
            double likesScore = post.getLikes().size() * LIKES_WEIGHT;
            double myCommentsScore = commentsMap.get(post.getCreator()).stream().mapToDouble(c ->
                    1f/(ChronoUnit.DAYS.between(c.getAddedDate().toInstant(), new Date().toInstant())+1)).sum() * MY_COMMENTS_WEIGHT;
            double addedDateScore = 86400f/(ChronoUnit.SECONDS.between(post.getAddedDate().toInstant(), new Date().toInstant())+1) * ADDED_DATE_WEIGHT;
            double totalScore = myLikesScore + likesScore + myCommentsScore + addedDateScore;
            postsToShowScores.put(post, totalScore);
        }

        List<Post> posts = postsToShowScores.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).map(Map.Entry::getKey).collect(Collectors.toList());
//        sortedPosts.forEach(s -> {
//            System.out.printf("ID:%d, score:%f\n", s.getKey().getPostId(), s.getValue());
//        });
//        List<Post> posts = sortedPosts.map(Map.Entry::getKey).collect(Collectors.toList());
        posts.forEach(s -> {
            System.out.printf("ID:%d\n", s.getPostId());
        });

        Page<Post> page = new PageImpl<>(posts, pageable, posts.size());
        return page;
    }


}
