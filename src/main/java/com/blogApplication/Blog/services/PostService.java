package com.blogApplication.Blog.services;

import java.util.List;


import org.springframework.stereotype.Service;

import com.blogApplication.Blog.dtos.PostResponse;
import com.blogApplication.Blog.models.Post;



@Service
public interface PostService {
    Post createPost(Post post, Integer userId, Integer categoryId);

    Post updatePost(Post postDto, int postId);

    void deletePost(int postId);

    Post getPostById(int postId);

    PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);

    List<Post> getPostsByCategory(int categoryId);

    List<Post> getPostsByUser(int userId);

    List<Post> searchPosts(String keyword);
}
