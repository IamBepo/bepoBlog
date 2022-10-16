package com.blog.myblogsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.myblogsystem.constants.CodeConstants;
import com.blog.myblogsystem.exception.LoginException;
import com.blog.myblogsystem.mapper.UserLoginMapper;
import com.blog.myblogsystem.pojo.dto.UserCustomDetail;
import com.blog.myblogsystem.pojo.dto.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserLoginMapper userLoginMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<UserLoginDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", Integer.parseInt(username));
        UserLoginDTO user = userLoginMapper.selectOne(queryWrapper);
        if(Objects.isNull(user)){
            throw new LoginException("用户名不存在",CodeConstants.CODE_500);
        }

        List<String> list = new ArrayList<>(Arrays.asList("admin"));
        return new UserCustomDetail(user,list);
    }
}
