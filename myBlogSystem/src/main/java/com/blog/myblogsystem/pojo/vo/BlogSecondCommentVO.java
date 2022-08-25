package com.blog.myblogsystem.pojo.vo;

import com.blog.myblogsystem.pojo.dto.UserInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogSecondCommentVO {
    private Integer id;
    private Integer firstId;
    private Integer userId;
    private Integer replyUserId;
    private UserInfoDTO userInfo;
    private UserInfoDTO replyUserInfo;
    private String content;
    private String updateTime;
    private Integer type;
}
