package com.blog.myblogsystem.pojo.dto;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName blog_cover
 */
@TableName(value ="blog_cover")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogCoverDTO implements Serializable {

    public BlogCoverDTO(String url) {
        this.url = url;
    }

    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 图片链接
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