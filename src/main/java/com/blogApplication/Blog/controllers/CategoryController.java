package com.blogApplication.Blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApplication.Blog.dtos.CategoryDto;
import com.blogApplication.Blog.exceptions.Msg;
import com.blogApplication.Blog.models.Category;
import com.blogApplication.Blog.repos.CategoryRepo;
import com.blogApplication.Blog.services.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
@Tag(name = "Category Management APIs")
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;
    @Autowired
    private final CategoryRepo categoryRepo;

    @GetMapping
    @Operation(summary = "List of categories", description = "All CATEGORIES")
    @ApiResponse(description = "Display of all categories")
    public ResponseEntity<List<Category>> all() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    @Operation(summary = "Add Category", description = "ADD CATEGORIES")
    public ResponseEntity<Msg> addCategory(@Valid @RequestBody CategoryDto categoryDto) {
        if (categoryRepo.existsByCategoryTitle(categoryDto.getCategoryTitle())) {
            return ResponseEntity.ok(new Msg("Category already exists", HttpStatus.CONFLICT.value()));
        }
        Category category = new Category();
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        categoryService.addCategory(category);
        return ResponseEntity.ok(new Msg("Category added successfully", HttpStatus.OK.value()));
    }

    @Operation(summary = "Update Category", description = "UPDATE CATEGORIES")
    @PutMapping("/{id}")
    public ResponseEntity<Msg> updateCategory(@PathVariable int id, @Valid @RequestBody CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        categoryService.updateCategory(category, id);
        return ResponseEntity.ok(new Msg("Category updated successfully", HttpStatus.OK.value()));
    }

    @Operation(summary = "Delete Category", description = "DELETE CATEGORIES")
    @DeleteMapping("/{id}")
    public ResponseEntity<Msg> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(new Msg("Category deleted successfully", HttpStatus.OK.value()));

    }

    @Operation(summary = "Get Category By Id", description = "GET CATEGORY BY ID")
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable int id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

}
