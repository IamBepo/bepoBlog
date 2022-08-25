package com.blog.myblogsystem.exception;

import lombok.Data;

@Data
public class LoginException extends RuntimeException{
    private String code;

    public LoginException(String message, String code) {
        super(message);
        this.code = code;
    }
}
