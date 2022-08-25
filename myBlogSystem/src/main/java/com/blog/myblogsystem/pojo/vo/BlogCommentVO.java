package com.blog.myblogsystem.pojo.vo;

import com.blog.myblogsystem.pojo.dto.UserInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogCommentVO {
    private Integer id;
    private String content;
    private Integer userId;
    private String  updateTime;
    private UserInfoDTO userInfo;
    private List<BlogSecondCommentVO> secondComment;

    public BlogCommentVO(Integer id, String content, Integer userId, String updateTime) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.updateTime = updateTime;
    }
}
