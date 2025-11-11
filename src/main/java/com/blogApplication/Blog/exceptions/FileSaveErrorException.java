package com.blogApplication.Blog.exceptions;

public class FileSaveErrorException extends RuntimeException {
    public FileSaveErrorException(String message) {
        super(message);
    }
}
