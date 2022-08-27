package com.blog.myblogsystem.pojo.dto;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName system_friend_url
 */
@TableName(value ="system_friend_url")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemFriendUrlDTO implements Serializable {
    /**
     * 唯一标识
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 站名
     */
    private String name;

    /**
     * 站点地址
     */
    private String url;

    /**
     * 逻辑删除，默认0
     */
    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}