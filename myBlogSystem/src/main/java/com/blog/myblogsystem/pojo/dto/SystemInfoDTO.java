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
 * @TableName system_info
 */
@TableName(value ="system_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemInfoDTO implements Serializable {
    /**
     * 唯一标识
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 站名
     */
    private String webTitle;

    /**
     * 站点LOGO
     */
    private String webLogo;

    /**
     * 导航栏标题
     */
    private String navTitle;

    /**
     * 关于博主链接
     */
    private String aboutBlogger;

    /**
     * 底部引言
     */
    private String underIntroduction;

    /**
     * 技术支持声明
     */
    private String underPoweredBy;

    /**
     * 版权
     */
    private String underCopyright;

    /**
     * 备案信息
     */
    private String underIcp;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}