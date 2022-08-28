package com.blog.myblogsystem.pojo.vo;

import com.blog.myblogsystem.pojo.dto.UserInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogCommentManageVO {
    private Integer id;
    private Integer userid;
    private String  name;
    private Integer bodyId;
    private Long articleId;
    private String title;
    private String content;
    private String type;
}
