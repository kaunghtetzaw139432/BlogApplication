package com.blogApplication.Blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApplication.Blog.dtos.UserDto;
import com.blogApplication.Blog.exceptions.Msg;
import com.blogApplication.Blog.models.User;
import com.blogApplication.Blog.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Tag(name = "User Management APIs")
public class UserController {
    @Autowired
    private final UserService userService;

    @Operation(summary = "Update User", description = "UPDATE USER")
    @PutMapping("/{id}")
    public ResponseEntity<Msg> updateUser(@Valid @PathVariable Integer id, @RequestBody UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAge(userDto.getAge());
        user.setGender(userDto.getGender());
        userService.updateUser(user, id);
        return ResponseEntity.ok(new Msg("User updated successfully", HttpStatus.OK.value()));
    }

    @Operation(summary = "Get user by Id", description = "GET USER BY ID")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation(summary = "List of users", description = "ALL USERS")
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.all();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Delete user", description = "DELETE USER")
    @DeleteMapping("/{id}")
    public ResponseEntity<Msg> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new Msg("User deleted successfully", HttpStatus.OK.value()));

    }

}
