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
 * @TableName power_role
 */
@TableName(value ="power_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PowerRoleDTO implements Serializable {
    /**
     * 唯一标识
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色键名
     */
    private String roleKey;

    /**
     * 权限等级
     */
    private Integer rank;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}