package com.blog.myblogsystem.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BlogRouterVO {
    private Integer id;
    private String path;
    private String name;
    private String component;
    private String title;
    private String redirect;
    private Integer rank;
    private Integer fatherId;
    private List<BlogRouterVO> nextRouter;
}
