package com.blog.myblogsystem.mapper;

import com.blog.myblogsystem.pojo.dto.BlogBodyDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.myblogsystem.pojo.vo.BlogAritcleHomeVO;
import com.blog.myblogsystem.pojo.vo.BlogArticleDetailVO;
import com.blog.myblogsystem.pojo.vo.BlogArticleManageVO;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
* @author Administrator
* @description 针对表【blog_body】的数据库操作Mapper
* @createDate 2022-08-14 11:26:11
* @Entity com.blog.myblogsystem.entity.pojo.BlogBody
*/
public interface BlogBodyMapper extends BaseMapper<BlogBodyDTO> {
    @Update("update blog_body set status = #{status} where id = #{id}")
    void updateArticleStatus(int id, int status);

    @Update("update blog_body set top_status = #{status} where id = #{id}")
    void updateArticleTop(int id, int status);

    @Update("update blog_body set recommend_status = #{status} where id = #{id}")
    void updateArticleRecommend(int id, int status);

    @Update("update blog_body set comment_status = #{status} where id = #{id}")
    void updateArticleComment(int id, int status);

    List<BlogArticleManageVO> listArticleManage(); //获取后台管理文章
    List<BlogArticleManageVO> listArticleManageBySearch(String content); //获取后台搜索文章
    List<BlogAritcleHomeVO> listArticleHomeBaseInfo(); //获取首页文章列表基本信息
    List<BlogAritcleHomeVO> listSearchArticle(String key); // 获取搜索文章
    BlogArticleDetailVO listArticleDetail(int id); //获取文章内容及关联信息
}




