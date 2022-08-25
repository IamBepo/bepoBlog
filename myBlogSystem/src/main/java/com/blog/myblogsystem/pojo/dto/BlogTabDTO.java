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
 * @TableName blog_tab
 */
@TableName(value ="blog_tab")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogTabDTO implements Serializable {
    public BlogTabDTO(String name, Timestamp updateTime) {
        this.name = name;
        this.updateTime = updateTime;
    }

    public BlogTabDTO(Integer id, Timestamp updateTime, Integer status) {
        this.id = id;
        this.updateTime = updateTime;
        this.status = status;
    }

    public BlogTabDTO(Integer id, String name, Timestamp updateTime) {
        this.id = id;
        this.name = name;
        this.updateTime = updateTime;
    }

    public BlogTabDTO(Integer id) {
        this.id = id;
    }

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 标签名
     */
    private String name;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

    /**
     * 开启关闭状态
     */
    private Integer status;

    /**
     *  逻辑删除
     */
    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}