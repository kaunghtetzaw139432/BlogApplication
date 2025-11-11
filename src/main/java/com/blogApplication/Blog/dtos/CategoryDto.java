package com.blogApplication.Blog.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryDto {
    private Integer categoryId;
    private String categoryTitle;
    private String categoryDescription;
}
