package com.blog.myblogsystem.service;

import com.blog.myblogsystem.pojo.dto.UserInfoDTO;
import com.blog.myblogsystem.pojo.dto.UserLoginDTO;
import com.blog.myblogsystem.pojo.vo.UserVO;
import com.blog.myblogsystem.result.JsonResult;

public interface UserService {
    Integer register(UserLoginDTO userLoginDTO, UserInfoDTO userInfoDTO); //注册接口
    UserVO logOn(UserLoginDTO userLoginDTO); //登录接口
}
