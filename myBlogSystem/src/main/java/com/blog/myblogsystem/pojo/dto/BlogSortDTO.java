package com.blog.myblogsystem.pojo.dto;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName blog_sort
 */
@TableName(value ="blog_sort")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogSortDTO implements Serializable {
    public BlogSortDTO(String name, Date updateTime) {
        this.name = name;
        this.updateTime = updateTime;
    }

    public BlogSortDTO(Integer id, Date updateTime, Integer status) {
        this.id = id;
        this.updateTime = updateTime;
        this.status = status;
    }

    public BlogSortDTO(Integer id, String name, Date updateTime) {
        this.id = id;
        this.name = name;
        this.updateTime = updateTime;
    }

    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *  分类名
     */
    private String name;

    /**
     *  更细时间
     */
    private Date updateTime;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}