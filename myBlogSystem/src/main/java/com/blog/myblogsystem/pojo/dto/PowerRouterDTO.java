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
 * @TableName power_router
 */
@TableName(value ="power_router")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PowerRouterDTO implements Serializable {
    /**
     * 唯一标识
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 路由path
     */
    private String path;

    /**
     * 路由名
     */
    private String name;

    /**
     * 路由组件
     */
    private String component;

    /**
     * 访问的最低级角色
     */
    private Integer roleId;

    /**
     * 父亲路由id
     */
    private Integer fatherId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}