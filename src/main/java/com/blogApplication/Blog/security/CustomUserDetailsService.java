package com.blogApplication.Blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogApplication.Blog.exceptions.UserNotFoundException;
import com.blogApplication.Blog.models.User;
import com.blogApplication.Blog.repos.UserRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username).orElseThrow(() -> new UserNotFoundException("No user with that id"));
        return user;
    }

}
