package com.blog.myblogsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.blog.myblogsystem.mapper")
public class MyBlogSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBlogSystemApplication.class, args);
    }

}