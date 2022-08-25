package com.blog.myblogsystem.pojo.vo;

import com.blog.myblogsystem.pojo.dto.UserInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogNewCommentVO {
    private Integer id;
    private Integer articleId;
    private String title;
    private String content;
    private Integer userId;
    private String  updateTime;
    private UserInfoDTO userInfo;
}
