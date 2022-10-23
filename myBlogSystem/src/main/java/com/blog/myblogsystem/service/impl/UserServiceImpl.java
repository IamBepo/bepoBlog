package com.blog.myblogsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.myblogsystem.constants.CodeConstants;
import com.blog.myblogsystem.mapper.*;
import com.blog.myblogsystem.pojo.dto.PowerRoleDTO;
import com.blog.myblogsystem.pojo.dto.UserInfoDTO;
import com.blog.myblogsystem.pojo.dto.UserLoginDTO;
import com.blog.myblogsystem.exception.LoginException;
import com.blog.myblogsystem.pojo.dto.UserRoleDTO;
import com.blog.myblogsystem.pojo.vo.BlogRouterVO;
import com.blog.myblogsystem.pojo.vo.UserInfoVO;
import com.blog.myblogsystem.pojo.vo.UserVO;
import com.blog.myblogsystem.result.JsonResult;
import com.blog.myblogsystem.service.UserService;
import com.blog.myblogsystem.utils.CommonUtils;
import com.blog.myblogsystem.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;

    @Autowired(required = false)
    private UserInfoMapper userInfoMapper;
    @Autowired(required = false)
    private UserLoginMapper userLoginMapper;
    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired(required = false)
    private PowerRoleMapper powerRoleMapper;
    @Autowired(required = false)
    private UserRoleMapper userRoleMapper;

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
         * 授权路由查询
         */
        UserVO userInfoAndRank = userMapper.getUserInfoAndRank(user.getId());
        List<BlogRouterVO> fatherRouterList = userMapper.listLessThanRankRouter(userInfoAndRank.getRoleRank());
        List<BlogRouterVO> blogRouterList = CommonUtils.makeRouter(fatherRouterList);
        userInfoAndRank.setRouterList(blogRouterList);

        String jwt = JwtUtil.createJWT(user.getId().toString());
        userInfoAndRank.setToken(jwt);
        return userInfoAndRank;
    }

    @Override
    public BlogRouterVO listAuthorizationRouter(String token) {
        return null;
    }

    @Override
    public UserVO logOnAuthorization(String token) {
        try{
            log.info(token);
            Claims claims = JwtUtil.parseJWT(token);
            String id = claims.getSubject();

            UserVO userInfoAndRank = userMapper.getUserInfoAndRank(Integer.parseInt(id));
            List<BlogRouterVO> fatherRouterList = userMapper.listLessThanRankRouter(userInfoAndRank.getRoleRank());
            List<BlogRouterVO> blogRouterList = CommonUtils.makeRouter(fatherRouterList);
            userInfoAndRank.setRouterList(blogRouterList);
            return userInfoAndRank;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<UserInfoVO> listUserInfo() {
        List<UserLoginDTO> userLogin = userLoginMapper.selectList(null);
        List<UserInfoVO> userInfo = new ArrayList<>();
        userLogin.stream().forEach(item -> {
            QueryWrapper<UserInfoDTO> u = new QueryWrapper<>();
            u.eq("id",item.getId());
            QueryWrapper<UserRoleDTO> ur = new QueryWrapper<>();
            ur.eq("user_id",item.getId());
            QueryWrapper<PowerRoleDTO> p = new QueryWrapper<>();
            p.eq("id",userRoleMapper.selectOne(ur).getRoleId());
            UserInfoVO userInfoVO = new UserInfoVO(item,
                    userInfoMapper.selectOne(u),
                    powerRoleMapper.selectOne(p)
                    );
            userInfo.add(userInfoVO);
        });
        return userInfo;
    }
}
