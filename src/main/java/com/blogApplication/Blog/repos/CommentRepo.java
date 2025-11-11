package com.blogApplication.Blog.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogApplication.Blog.models.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
