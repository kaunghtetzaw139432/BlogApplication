package com.blogApplication.Blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApplication.Blog.dtos.CommentDto;
import com.blogApplication.Blog.exceptions.Msg;
import com.blogApplication.Blog.models.Comment;
import com.blogApplication.Blog.services.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
@Tag(name = "Comment Management APIs")
public class CommentController {
    @Autowired
    private final CommentService commentService;

    @Operation(summary = "Create comment using by postId", description = "CREATE COMMENT")
    @PostMapping("/{postId}")
    public ResponseEntity<Msg> createPost(@RequestBody CommentDto commentDto, @PathVariable Integer postId) {
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        commentService.createComment(comment, postId);
        return ResponseEntity.ok(new Msg("Comment added successfully", HttpStatus.OK.value()));
    }

    @Operation(summary = "Delete comment", description = "DELETE COMMENT")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Msg> deleteComment(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok(new Msg("Comment deleted successfully", HttpStatus.OK.value()));
    }
}
