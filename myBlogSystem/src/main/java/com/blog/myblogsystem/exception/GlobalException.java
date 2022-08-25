package com.blog.myblogsystem.exception;

import com.blog.myblogsystem.result.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(LoginException.class)
    @ResponseBody
    public JsonResult loginError(LoginException loginException){
        return new JsonResult(loginException.getCode(),loginException.getMessage());
    }

    @ExceptionHandler(DbException.class)
    @ResponseBody
    public JsonResult dbError(DbException dbException){
        return new JsonResult(dbException.getCode(),dbException.getMessage());
    }
}
