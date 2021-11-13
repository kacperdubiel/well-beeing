package com.wellbeeing.wellbeeing.api.social;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.social.Post;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import com.wellbeeing.wellbeeing.service.account.UserService;
import com.wellbeeing.wellbeeing.service.files.FileService;
import com.wellbeeing.wellbeeing.service.social.LikeService;
import com.wellbeeing.wellbeeing.service.social.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "/post")
@RestController
public class PostController {

    @Autowired
    private PostService postService;
    private final UserService userService;
    private final ProfileService profileService;
    private final LikeService likeService;
    private final FileService fileService;

    public PostController(@Qualifier("postService") PostService postService,
                          @Qualifier("userService") UserService userService,
                          @Qualifier("profileService") ProfileService profileService,
                          @Qualifier("likeService") LikeService likeService,
                          @Qualifier("fileService") FileService fileService) {
        this.postService = postService;
        this.userService = userService;
        this.profileService = profileService;
        this.likeService = likeService;
        this.fileService = fileService;
    }

    @GetMapping(path = "/my")
    public ResponseEntity<?> getMyPosts(@PageableDefault(sort = {"addedDate"}, size = 20, direction = Sort.Direction.DESC) Pageable pageable, Principal principal) throws NotFoundException {
        UUID userId = userService.findUserIdByUsername(principal.getName());
        Profile profile = profileService.getProfileById(userId);
        Page<Post> pagePosts = postService.getMyPosts(profile, pageable);
        return new ResponseEntity<>(pagePosts, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getPostById(@PathVariable(value = "id") long postId) {
        return new ResponseEntity<>(postService.getPost(postId), HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<?> addPost(@RequestBody @NonNull Post post, Principal principal) throws NotFoundException {
        Post newPost = postService.addPost(post, principal.getName());
        return new ResponseEntity<>(newPost, HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePost(@PathVariable(value = "id") long postId, @RequestBody Map<String, Object> fields, Principal principal) throws NotFoundException {
        Post newPost = postService.partialUpdatePost(postId, fields, principal.getName());
        return new ResponseEntity<>(newPost, HttpStatus.OK);
    }

    @PostMapping("/import/{postId}")
    public ResponseEntity<?> importData(MultipartFile file, @PathVariable long postId, Principal principal) throws NotFoundException {

        String fileName = fileService.save(file);

        Post post = postService.getPost(postId);
        post.setPostImgPath(fileName);
        postService.updatePost(postId, post, principal.getName());

        return new ResponseEntity<>("Sent", HttpStatus.OK);
    }

    @GetMapping("/export/{postId}")
    public ResponseEntity<?> exportData(@PathVariable long postId) {
        Post post = postService.getPost(postId);
        Resource file = fileService.load(post.getPostImgPath());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "postPhoto")
                .contentType(MediaType.parseMediaType("image/png"))
                .body(file);
    }

    @PatchMapping(path = "/{id}/delete")
    public ResponseEntity<?> deletePost(@PathVariable(value = "id") long postId, Principal principal) throws NotFoundException {
        postService.deletePost(postId, principal.getName());
        Post deletedPost = postService.getPost(postId);
        return new ResponseEntity<>(deletedPost, HttpStatus.OK);
    }

    @PostMapping(path = "/{postId}/react")
    public ResponseEntity<?> reactToPost(@PathVariable long postId, Principal principal) throws NotFoundException {
        likeService.reactToPost(postId, principal.getName());
        return new ResponseEntity<>("Reaction made", HttpStatus.OK);
    }
}
