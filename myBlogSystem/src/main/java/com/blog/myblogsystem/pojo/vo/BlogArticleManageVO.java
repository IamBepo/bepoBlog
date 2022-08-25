package com.blog.myblogsystem.pojo.vo;

import com.blog.myblogsystem.pojo.dto.BlogBodyDTO;
import com.blog.myblogsystem.pojo.dto.BlogSortDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogArticleManageVO {
    private Long id;
    private String updateTime;
    private String title;
    private String sortName;
    private Integer viewCount;
    private Integer commentCount;
    private Integer status;
    private Integer topStatus;
    private Integer recommendStatus;
    private Integer commentStatus;
}
