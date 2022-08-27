package com.blog.myblogsystem.pojo.dto;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName blog_shields
 */
@TableName(value ="blog_shields")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogShieldsDTO implements Serializable {
    /**
     * 唯一标识
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 展示链接
     */
    private String shieldsUrl;

    /**
     * 跳转链接
     */
    private String url;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}