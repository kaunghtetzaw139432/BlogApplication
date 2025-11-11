package com.blogApplication.Blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogApplication.Blog.models.Category;

@Service
public interface CategoryService {
    List<Category> getAllCategories();

    Category addCategory(Category category);

    Category updateCategory(Category category, int id);

    void deleteCategory(int id);

    Category getCategoryById(int id);

}
