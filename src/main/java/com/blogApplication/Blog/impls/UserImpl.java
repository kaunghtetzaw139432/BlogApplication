package com.blogApplication.Blog.impls;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.blogApplication.Blog.exceptions.UserNotFoundException;
import com.blogApplication.Blog.models.User;
import com.blogApplication.Blog.repos.UserRepo;
import com.blogApplication.Blog.services.UserService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserImpl implements UserService {

    @Autowired
    private final UserRepo userRepo;

    @Override
    @Transactional
    public User createUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User updateUser(User user, int userId) {
      User existingUser=userRepo.findById(userId).orElseThrow(()->new UserNotFoundException("No user with this id"));
      if (existingUser!=null) {
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setAge(user.getAge());
        existingUser.setGender(user.getGender());
        return userRepo.save(existingUser);
      } else {
        throw new UserNotFoundException("No user with this id");
        
      }
    }

    @Override
    public void deleteUser(int userId) {
    User user=getUserById(userId);
    userRepo.delete(user);
    }

    @Override
    public List<User> all() {
         return userRepo.findAll();
    }

    @Override
    public User getUserById(int userId) {
       return userRepo.findById(userId).orElseThrow(()->new UserNotFoundException("No user with this id"));
    }

   

  
}
