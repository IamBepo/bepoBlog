package com.blog.myblogsystem.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserVO {
    private Integer id;
    private String userid;
    private String userName;
    private String email;
    private String avatar;
    private String roleName;
    private String roleKey;
    private Integer roleRank;
    private List<BlogRouterVO> routerList;
    private String token;
}
