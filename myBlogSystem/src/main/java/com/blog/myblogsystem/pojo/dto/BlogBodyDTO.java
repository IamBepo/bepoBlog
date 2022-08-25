package com.blog.myblogsystem.pojo.dto;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName blog_body
 */
@TableName(value ="blog_body")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogBodyDTO implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面图片id
     */
    private Integer coverId;

    /**
     * 内容id
     */
    private Long contentId;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

    /**
     * 分类id
     */
    private Integer sortId;

    /**
     * 标签id
     */
    private String tabId;

    /**
     * 浏览量,默认0
     */
    private Integer viewCount;

    /**
     * 评论数,默认0
     */
    private Integer commentCount;

    /**
     * 开启/关闭状态，默认1
     */
    private Integer status;

    /**
     * 置顶，默认0不置顶，等级制
     */
    private Integer topStatus;

    /**
     * 推荐，默认0，等级制
     */
    private Integer recommendStatus;

    /**
     * 开启/关闭评论
     */
    private Integer commentStatus;

    /**
     *  逻辑删除
     */
    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}