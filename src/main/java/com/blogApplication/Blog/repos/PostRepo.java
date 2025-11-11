package com.blogApplication.Blog.repos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blogApplication.Blog.models.Category;
import com.blogApplication.Blog.models.Post;
import com.blogApplication.Blog.models.User;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByCategory(Category category);

    List<Post> findByUser(User user);

    List<Post> findByPostTitleContainingIgnoreCase(String postTitle);

    @Query(value = "SELECT p FROM Post p JOIN FETCH p.comments", countQuery = "SELECT count(p) FROM Post p")
    Page<Post> findAllPostsWithComments(Pageable pageable);

}
