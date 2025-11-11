package com.blogApplication.Blog.exceptions;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(String message) {
        super(message);
    }

}
