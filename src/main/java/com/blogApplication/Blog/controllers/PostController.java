package com.blogApplication.Blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.blogApplication.Blog.dtos.PostDto;
import com.blogApplication.Blog.dtos.PostResponse;
import com.blogApplication.Blog.exceptions.Msg;
import com.blogApplication.Blog.models.Post;
import com.blogApplication.Blog.services.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
@Tag(name = "Post Management APIs")
public class PostController {
    @Autowired
    private final PostService postService;

    @Operation(summary = "Create Post using userId and postId", description = "ADD COMMENT")
    @PostMapping("/{userId}/{categoryId}")
    public ResponseEntity<Post> addpost(@RequestBody PostDto postDto, @PathVariable int userId,
            @PathVariable int categoryId) {
        Post post = new Post();
        post.setPostTitle(postDto.getPostTitle());
        post.setPostContent(postDto.getPostContent());
        post.setImageName("cat.jpg");
        postService.createPost(post, userId, categoryId);
        return ResponseEntity.ok().body(post);

    }

    @Operation(summary = "Update Post", description = "UPDATE POST")
    @PutMapping("/{id}")
    public ResponseEntity<Msg> updatePost(@PathVariable int id, @RequestBody PostDto postDto) {
        Post post = new Post();
        post.setPostTitle(postDto.getPostTitle());
        post.setPostContent(postDto.getPostContent());
        post.setImageName("dog.jpg");
        postService.updatePost(post, id);
        return ResponseEntity.ok(new Msg("Post updated successfully", HttpStatus.OK.value()));
    }

    @Operation(summary = "List of posts", description = "ALL POSTS")
    @GetMapping("/all")
    public ResponseEntity<PostResponse> all(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir) {
        PostResponse postResponse = postService.getAllPost(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

    @Operation(summary = "Get post by categoryId", description = "GET POST BY CATEGORY")
    @PostMapping("/category/{id}")
    public ResponseEntity<List<Post>> getPostByCategory(@PathVariable int id) {
        List<Post> posts = postService.getPostsByCategory(id);
        return ResponseEntity.ok(posts);
    }

    @Operation(summary = "Get post by userId", description = "GET POST BY USER")
    @PostMapping("/user/{id}")
    public ResponseEntity<List<Post>> getPostByUser(@PathVariable int id) {
        List<Post> posts = postService.getPostsByUser(id);
        return ResponseEntity.ok(posts);
    }

     @Operation(summary = "Delete post", description = "DELETE POST")
    @DeleteMapping("/{id}")
    public ResponseEntity<Msg> deletePost(@PathVariable int id) {
        postService.deletePost(id);
        return ResponseEntity.ok(new Msg("Post deleted successfully", HttpStatus.OK.value()));
    }

     @Operation(summary = "Search post by title", description = "SEARCHING POSTS BY TITLE")
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Post>> searchPosts(@PathVariable String keyword) {
        List<Post> posts = postService.searchPosts(keyword);
        return ResponseEntity.ok(posts);
    }

}
