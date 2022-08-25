package com.blog.myblogsystem.pojo.vo;

import com.blog.myblogsystem.pojo.dto.BlogArticleDTO;
import com.blog.myblogsystem.pojo.dto.BlogTabDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogArticleDetailVO {
    private int id;
    private String title;
    private String updateTime;
    private String sortName;
    private String blogTabId;
    private List<BlogTabDTO> blogTab;
    private Long contentId;
    private BlogArticleDTO article;
    private int viewCount;
    private int commentCount;
}
