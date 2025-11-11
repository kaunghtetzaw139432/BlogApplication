package com.blogApplication.Blog.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogApplication.Blog.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);

    public Optional<User> findByEmail(String email);

}
