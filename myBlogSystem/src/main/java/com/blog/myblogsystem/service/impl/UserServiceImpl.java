package com.blog.myblogsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.myblogsystem.constants.CodeConstants;
import com.blog.myblogsystem.mapper.UserMapper;
import com.blog.myblogsystem.pojo.dto.UserInfoDTO;
import com.blog.myblogsystem.pojo.dto.UserLoginDTO;
import com.blog.myblogsystem.exception.LoginException;
import com.blog.myblogsystem.mapper.UserInfoMapper;
import com.blog.myblogsystem.mapper.UserLoginMapper;
import com.blog.myblogsystem.pojo.vo.BlogRouterVO;
import com.blog.myblogsystem.pojo.vo.UserVO;
import com.blog.myblogsystem.result.JsonResult;
import com.blog.myblogsystem.service.UserService;
import com.blog.myblogsystem.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;

    @Autowired(required = false)
    private UserInfoMapper userInfoMapper;
    @Autowired(required = false)
    private UserLoginMapper userLoginMapper;
    @Autowired(required = false)
    private UserMapper userMapper;

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
    public UserVO logOn(UserLoginDTO userLoginDTO) {
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

        /**
         * 授权查询
         */
        UserVO userInfoAndRank = userMapper.getUserInfoAndRank(user.getId());
        List<BlogRouterVO> fatherRouterList = userMapper.listLessThanRankRouter(userInfoAndRank.getRoleRank(),0);
        fatherRouterList.forEach(aItem -> {
            List<BlogRouterVO> childRouter = userMapper.listLessThanRankRouter(aItem.getRank(), aItem.getId());
            aItem.setNextRouter(childRouter);
            childRouter.forEach(bItem -> {
                List<BlogRouterVO> bChildRouter = userMapper.listLessThanRankRouter(bItem.getRank(), bItem.getId());
                bItem.setNextRouter(bChildRouter);
            });
        });
        userInfoAndRank.setRouterList(fatherRouterList);

        String jwt = JwtUtil.createJWT(userLoginDTO.getUserid());
        userInfoAndRank.setToken(jwt);
        return userInfoAndRank;
    }
}
