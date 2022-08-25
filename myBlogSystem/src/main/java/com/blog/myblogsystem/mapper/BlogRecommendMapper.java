package com.blog.myblogsystem.mapper;

import com.blog.myblogsystem.pojo.dto.BlogRecommendDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.myblogsystem.pojo.vo.BlogListRecommendVO;
import com.blog.myblogsystem.pojo.vo.BlogWalkRecommendVO;

import java.util.List;

/**
* @author Administrator
* @description 针对表【blog_recommend】的数据库操作Mapper
* @createDate 2022-08-25 14:38:14
* @Entity com.blog.myblogsystem.pojo.dto.BlogRecommendDTO
*/
public interface BlogRecommendMapper extends BaseMapper<BlogRecommendDTO> {
    List<BlogListRecommendVO> listListRecommend(); //获取所有列表推荐
    List<BlogWalkRecommendVO> listWalkRecommend(); //获取所有走马灯推荐

}




