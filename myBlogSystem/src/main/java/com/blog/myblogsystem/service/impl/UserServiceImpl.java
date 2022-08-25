package com.blog.myblogsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.myblogsystem.constants.CodeConstants;
import com.blog.myblogsystem.pojo.dto.UserInfoDTO;
import com.blog.myblogsystem.pojo.dto.UserLoginDTO;
import com.blog.myblogsystem.exception.LoginException;
import com.blog.myblogsystem.mapper.UserInfoMapper;
import com.blog.myblogsystem.mapper.UserLoginMapper;
import com.blog.myblogsystem.result.JsonResult;
import com.blog.myblogsystem.service.UserService;
import com.blog.myblogsystem.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    PasswordEncoder passwordEncoder;

    @Autowired(required = false)
    UserInfoMapper userInfoMapper;
    @Autowired(required = false)
    UserLoginMapper userLoginMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer register(UserLoginDTO userLoginDTO, UserInfoDTO userInfoDTO) {
        QueryWrapper<UserLoginDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userLoginDTO.getUserid());
        if(userLoginMapper.selectCount(queryWrapper) == 0){
            userLoginDTO.setPassword(passwordEncoder.encode(userLoginDTO.getPassword()));
            userLoginMapper.insert(userLoginDTO);
            userInfoDTO.setId(userLoginDTO.getId());
            return userInfoMapper.insert(userInfoDTO);
        }
        else{
            throw new LoginException("用户名存在", CodeConstants.CODE_500);
        }
    }

    @Override
    public JsonResult logOn(UserLoginDTO userLoginDTO) {
        if(Objects.isNull(userLoginDTO.getUserid())||Objects.isNull(userLoginDTO.getPassword())){
            throw new LoginException("空参数",CodeConstants.CODE_500);
        }
        QueryWrapper<UserLoginDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userLoginDTO.getUserid());
        UserLoginDTO user = userLoginMapper.selectOne(queryWrapper);
        if(Objects.isNull(user)){
            throw new LoginException("用户名不存在",CodeConstants.CODE_500);
        }
        if(!passwordEncoder.matches(userLoginDTO.getPassword(),user.getPassword())){
            throw new LoginException("用户名或密码错误",CodeConstants.CODE_500);
        }
        String jwt = JwtUtil.createJWT(userLoginDTO.getUserid());
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        return new JsonResult(map,CodeConstants.CODE_200);
    }
}
