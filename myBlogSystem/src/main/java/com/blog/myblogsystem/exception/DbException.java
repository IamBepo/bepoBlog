package com.blog.myblogsystem.exception;

import lombok.Data;

@Data
public class DbException extends RuntimeException{
    private String code;

    public DbException(String message, String code) {
        super(message);
        this.code = code;
    }
}
