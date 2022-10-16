package com.blog.myblogsystem.mapper;

import com.blog.myblogsystem.pojo.vo.BlogRouterVO;
import com.blog.myblogsystem.pojo.vo.UserVO;

import java.util.List;

public interface UserMapper{
    UserVO getUserInfoAndRank(Integer userId); //获取用户基础信息And用户权限等级
    List<BlogRouterVO> listLessThanRankFatherRouter(Integer rank,Integer fatherId); //获取小于等于rank的id路由
    List<BlogRouterVO> listLessThanRankRouter(Integer rank); //获取小于等于rank的路由
}
