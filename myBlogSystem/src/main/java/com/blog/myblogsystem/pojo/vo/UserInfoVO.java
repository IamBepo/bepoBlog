package com.blog.myblogsystem.pojo.vo;

import com.blog.myblogsystem.pojo.dto.PowerRoleDTO;
import com.blog.myblogsystem.pojo.dto.UserInfoDTO;
import com.blog.myblogsystem.pojo.dto.UserLoginDTO;
import com.blog.myblogsystem.pojo.dto.UserRoleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInfoVO {
    private UserLoginDTO userLogin;
    private UserInfoDTO userInfo;
    private PowerRoleDTO powerRole;
}
