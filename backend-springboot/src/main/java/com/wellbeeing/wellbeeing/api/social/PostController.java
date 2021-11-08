package com.wellbeeing.wellbeeing.api.social;

import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.social.Post;
import com.wellbeeing.wellbeeing.domain.social.RoleRequest;
import com.wellbeeing.wellbeeing.service.files.FileService;
import com.wellbeeing.wellbeeing.service.social.PostService;
import com.wellbeeing.wellbeeing.service.social.RoleRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "/post")
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    public PostController(@Qualifier("postService") PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<?> getPostById(@PathVariable(value = "id") long postId) {
        return new ResponseEntity<>(postService.getPost(postId), HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<?> addPost(@RequestBody @NonNull Post post, Principal principal) throws NotFoundException {
        Post newPost = postService.addPost(post, principal.getName());
        return new ResponseEntity<>(newPost, HttpStatus.OK);
    }
}
