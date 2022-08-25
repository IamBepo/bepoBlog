package com.blog.myblogsystem.web;

import com.blog.myblogsystem.constants.CodeConstants;
import com.blog.myblogsystem.constants.MsgConstants;
import com.blog.myblogsystem.constants.UserConstants;
import com.blog.myblogsystem.pojo.dto.UserInfoDTO;
import com.blog.myblogsystem.pojo.dto.UserLoginDTO;
import com.blog.myblogsystem.result.JsonResult;
import com.blog.myblogsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;


    @RequestMapping("/signUp")
    public JsonResult sginUp(String username,String password,String email){
        UserLoginDTO userLoginDTO = new UserLoginDTO(username,password);
        UserInfoDTO userInfoDTO = new UserInfoDTO(username, UserConstants.DEFAULT_NAME,UserConstants.DEFAULT_AVATAR,email);
        userService.register(userLoginDTO, userInfoDTO);
        return new JsonResult(CodeConstants.CODE_200, MsgConstants.SUCCESS);
    }

    @RequestMapping("/signIn")
    public JsonResult signIn(UserLoginDTO userLoginDTO){
        return userService.logOn(userLoginDTO);
    }

}
