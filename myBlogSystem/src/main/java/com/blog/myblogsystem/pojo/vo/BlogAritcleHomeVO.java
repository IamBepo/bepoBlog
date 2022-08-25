package com.blog.myblogsystem.pojo.vo;

import com.blog.myblogsystem.pojo.dto.BlogTabDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogAritcleHomeVO {
    private int id;
    private String title;
    private String sortName;
    private String blogTabId;
    private List<BlogTabDTO> blogTab;
    private String blogCover;
    private String updateTime;
    private int viewCount;
    private int commentCount;
    private int status;
    private int topStatus;
}