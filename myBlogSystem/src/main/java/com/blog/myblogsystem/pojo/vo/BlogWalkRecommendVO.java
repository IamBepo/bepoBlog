package com.blog.myblogsystem.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogWalkRecommendVO {
    private Integer id;
    private Integer recommendId;
    private String title;
    private String cover;
    private Integer power;
}
