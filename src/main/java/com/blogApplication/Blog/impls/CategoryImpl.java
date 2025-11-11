package com.blogApplication.Blog.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.blogApplication.Blog.exceptions.CategoryNotFoundException;
import com.blogApplication.Blog.models.Category;
import com.blogApplication.Blog.repos.CategoryRepo;
import com.blogApplication.Blog.services.CategoryService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryImpl implements CategoryService {
    @Autowired
    private final CategoryRepo categoryRepo;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    @Transactional
    public Category addCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Category updateCategory(Category category, int id) {
        Category existingCategory = categoryRepo.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("No category with this id"));
        if (existingCategory != null) {
            existingCategory.setCategoryTitle(category.getCategoryTitle());
            existingCategory.setCategoryDescription(category.getCategoryDescription());
            return categoryRepo.save(existingCategory);
        } else {
            throw new CategoryNotFoundException("No category with this id");
        }
    }

    @Override
    public void deleteCategory(int id) {
        Category category = getCategoryById(id);
        categoryRepo.delete(category);
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("No category with this id"));
    }

   

}
