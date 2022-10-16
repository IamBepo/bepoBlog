package com.blog.myblogsystem.pojo.dto;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 
 * @TableName user_login
 */
@TableName(value ="user_login")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @NotBlank(message = "用户名不能为空")
    @Length(min = 4,max = 20,message = "长度不满足要求")
    private String userid;

    /**
     * 
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 8,max = 20,message = "长度不满足要求")
    private String password;

    /**
     * 
     */
    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public UserLoginDTO(String userid, String password) {
        this.userid = userid;
        this.password = password;
    }

    public UserLoginDTO(Integer id) {
        this.id = id;
    }
}