package com.blogApplication.Blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApplication.Blog.dtos.Request;
import com.blogApplication.Blog.dtos.Response;
import com.blogApplication.Blog.dtos.UserDto;
import com.blogApplication.Blog.exceptions.Msg;
import com.blogApplication.Blog.models.User;
import com.blogApplication.Blog.repos.UserRepo;
import com.blogApplication.Blog.security.JwtHelper;
import com.blogApplication.Blog.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Tag(name = "Authentication Management APIs")
public class AuthController {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtHelper helper;
    @Autowired
    private UserService userService;

    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Operation(summary = "Login User", description = "Login User")
    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody Request request) {
        doAuthenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = helper.generateToken(userDetails);
        Response response = Response.builder()
                .token(token)
                .username(userDetails.getUsername())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public void doAuthenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                password);
        try {
            authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHalder() {
        return "Credential Invalid";
    }

    @Operation(summary = "Create new user account", description = "Creating a new user")
    @PostMapping("/create-user")
    public ResponseEntity<Msg> createuser(@Valid @RequestBody UserDto userDto) {
        if (userRepo.existsByEmail(userDto.getEmail())) {
            return ResponseEntity.ok(new Msg("User already exist", HttpStatus.CONFLICT.value()));
        }
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAge(userDto.getAge());
        user.setGender(userDto.getGender());
        userService.createUser(user);
        return ResponseEntity.ok(new Msg("User created successfully", HttpStatus.OK.value()));
    }

}
