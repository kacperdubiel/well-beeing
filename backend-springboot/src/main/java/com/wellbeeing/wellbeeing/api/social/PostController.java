package com.wellbeeing.wellbeeing.api.social;

import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.social.Post;
import com.wellbeeing.wellbeeing.domain.social.RoleRequest;
import com.wellbeeing.wellbeeing.service.files.FileService;
import com.wellbeeing.wellbeeing.service.social.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "/post")
@RestController
public class PostController {

    @Autowired
    private PostService postService;
    private final FileService fileService;

    public PostController(@Qualifier("postService") PostService postService, @Qualifier("fileService") FileService fileService) {
        this.postService = postService;
        this.fileService = fileService;
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
}
