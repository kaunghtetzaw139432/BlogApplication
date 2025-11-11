package com.blogApplication.Blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogApplication.Blog.models.User;


@Service
public interface UserService {
    User createUser(User user);

    User updateUser(User user, int userId);

    void deleteUser(int userId);

    List<User>all();

    User getUserById(int userId);
}
