package com.blogApplication.Blog.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogApplication.Blog.models.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
    boolean existsByCategoryTitle(String categoryTitle);
}

