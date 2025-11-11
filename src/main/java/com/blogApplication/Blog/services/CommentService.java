package com.blogApplication.Blog.services;

import org.springframework.stereotype.Service;


import com.blogApplication.Blog.models.Comment;

@Service
public interface CommentService {
    Comment createComment(Comment comment, Integer postId);

    void deleteComment(Integer commentId);
}
