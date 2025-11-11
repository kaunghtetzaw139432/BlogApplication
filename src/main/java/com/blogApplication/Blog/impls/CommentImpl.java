package com.blogApplication.Blog.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blogApplication.Blog.exceptions.CommentNotFoundException;
import com.blogApplication.Blog.exceptions.PostNotFoundException;
import com.blogApplication.Blog.models.Comment;
import com.blogApplication.Blog.models.Post;
import com.blogApplication.Blog.repos.CommentRepo;
import com.blogApplication.Blog.repos.PostRepo;
import com.blogApplication.Blog.services.CommentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentImpl implements CommentService {
    @Autowired
    private final CommentRepo commentRepo;
    @Autowired
    private final PostRepo postRepo;

    @Override
    @Transactional
    public Comment createComment(Comment comment, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new PostNotFoundException("No post with that id"));
        comment.setContent(comment.getContent());
        comment.setPost(post);
        return commentRepo.save(comment);

    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("No comment with that id"));
        commentRepo.delete(comment);
    }

}
