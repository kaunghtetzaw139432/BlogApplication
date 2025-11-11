package com.blogApplication.Blog.dtos;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {

    private String name;

    private String email;

    private String password;

    private int age;

    private int id;

    private String gender;

}
