package com.wellbeeing.wellbeeing.api.social;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.social.Comment;
import com.wellbeeing.wellbeeing.domain.social.Post;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import com.wellbeeing.wellbeeing.service.account.UserService;
import com.wellbeeing.wellbeeing.service.files.FileService;
import com.wellbeeing.wellbeeing.service.social.CommentService;
import com.wellbeeing.wellbeeing.service.social.LikeService;
import com.wellbeeing.wellbeeing.service.social.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;
    private final UserService userService;
    private final ProfileService profileService;

    public CommentController(@Qualifier("commentService") CommentService commentService,
                          @Qualifier("userService") UserService userService,
                          @Qualifier("profileService") ProfileService profileService) {
        this.commentService = commentService;
        this.userService = userService;
        this.profileService = profileService;
    }


    @GetMapping(path = "/post/{postId}/comments")
    public ResponseEntity<?> getCommentsByPostId(@PathVariable long postId, @PageableDefault(sort = {"addedDate"}, size = 5, direction = Sort.Direction.DESC) Pageable pageable) throws NotFoundException {
        Page<Comment> pageComments = commentService.getCommentsByPostId(postId, pageable);
        return new ResponseEntity<>(pageComments, HttpStatus.OK);
    }

    @PostMapping(path = "/post/{postId}/comment")
    public ResponseEntity<?> addComment(@PathVariable long postId, @RequestBody @NonNull Comment comment, Principal principal) throws NotFoundException {
        Comment newComment = commentService.addComment(postId, comment, principal.getName());
        return new ResponseEntity<>(newComment, HttpStatus.OK);
    }

    @PatchMapping(path = "/comment/{commentId}/delete")
    public ResponseEntity<?> deleteComment(@PathVariable long commentId, Principal principal) throws NotFoundException {
        commentService.deleteComment(commentId, principal.getName());
        Comment deletedComment = commentService.getComment(commentId);
        return new ResponseEntity<>(deletedComment, HttpStatus.OK);
    }

    @PutMapping(path = "/comment/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable long commentId, @RequestBody @NonNull Comment comment, Principal principal) throws NotFoundException {
        Comment newComment = commentService.updateComment(commentId, comment, principal.getName());
        return new ResponseEntity<>(newComment, HttpStatus.OK);
    }
}
