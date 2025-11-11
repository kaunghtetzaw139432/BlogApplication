package com.blogApplication.Blog.dtos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private int id;
    private String postTitle;
    private String postContent;
    private String imageName;
    private Date addedDate;

    // Instead of putting the whole User and Category, keep only the simple fields
    private UserDto user;
    private CategoryDto category;

    private Set<CommentDto> comments = new HashSet<>();
}
