package com.blogApplication.Blog.exceptions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Msg {
    private String message;
    private int status;
}
