package com.blog.myblogsystem.pojo.dto;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName blog_comment_first
 */
@TableName(value ="blog_comment_first")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogCommentFirstDTO implements Serializable {
    /**
     * 评论标识id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 评论对应文章id
     */
    private Integer articleId;

    /**
     * 评论用户id
     */
    private Integer userId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除,默认0
     */
    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}