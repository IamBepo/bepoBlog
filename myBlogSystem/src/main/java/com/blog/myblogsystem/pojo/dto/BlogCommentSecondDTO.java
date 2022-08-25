package com.blog.myblogsystem.pojo.dto;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName blog_comment_second
 */
@TableName(value ="blog_comment_second")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogCommentSecondDTO implements Serializable {
    /**
     * 二级评论id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 对应一级评论id
     */
    private Integer firstId;

    /**
     * 评论用户id
     */
    private Integer userId;

    /**
     * 回复的用户id
     */
    private Integer replyUserId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 回复类型，2第二层，3第三层
     */
    private Integer type;

    /**
     * 逻辑删除,默认0
     */
    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}