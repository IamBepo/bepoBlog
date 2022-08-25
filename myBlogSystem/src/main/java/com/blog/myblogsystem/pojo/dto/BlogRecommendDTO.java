package com.blog.myblogsystem.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName blog_recommend
 */
@TableName(value ="blog_recommend")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogRecommendDTO implements Serializable {
    /**
     * 推荐标识
     */
    @TableId
    private Integer id;

    /**
     * 对应blogId
     */
    private Integer bodyId;

    /**
     * 推荐开启关闭状态
     */
    private Integer status;

    /**
     * 推荐等级
     */
    private String level;

    /**
     * 权重
     */
    private Integer power;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public BlogRecommendDTO(Integer bodyId, Integer status, String level, Integer power) {
        this.bodyId = bodyId;
        this.status = status;
        this.level = level;
        this.power = power;
    }

    public BlogRecommendDTO(Integer id, Integer power) {
        this.id = id;
        this.power = power;
    }
}