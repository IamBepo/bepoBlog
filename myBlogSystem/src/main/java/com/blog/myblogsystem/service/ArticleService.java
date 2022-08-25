package com.blog.myblogsystem.service;

import com.blog.myblogsystem.pojo.dto.*;
import com.blog.myblogsystem.pojo.vo.BlogAritcleHomeVO;
import com.blog.myblogsystem.pojo.vo.BlogArticleDetailVO;
import com.blog.myblogsystem.pojo.vo.BlogArticleManageVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleService {
    /*管理标签*/
    List<BlogTabDTO> listBlogTab(); //获取所有标签
    List<BlogTabDTO> listBlogTabBySearch(String content); //搜索标签
    Integer saveBlogTab(String name); //新增标签
    Integer updateBlogTabStatus(int id,int status); //修改标签开启/关闭状态
    Integer updateBlogTabName(int id,String newName); //修改标签名
    Integer removeBlogTab(int id); //删除标签

    /*管理分类*/
    List<BlogSortDTO> listBlogSort(); //获取所有分类
    List<BlogSortDTO> listBlogSortBySearch(String content); //搜索分类
    void saveBlogSort(String name); //新增分类
    void updateBlogSortStatus(int id,int status); //修改分类开启/关闭状态
    void updateBlogSortName(int id,String newName); //修改分类名
    void removeBlogSort(int id); //删除分类

    /*后台管理封面图片*/
    void saveCoverImg(MultipartFile file); //上传封面图片
    List<BlogCoverDTO> listBlogCover(); //获取所有封面图片
    void removeBlogCover(int id); //删除id封面图片

    /*管理文章*/
    void saveArticle(BlogBodyDTO blogBodyDTO,String content); //新增文章
    List<BlogArticleManageVO> listAritcleManage(); //获取后台管理文章列表
    List<BlogArticleManageVO> listArticleBySearchTitle(String content); //搜索文章-根据标题
    List<BlogAritcleHomeVO> listArticleHome(); //获取首页文章展示列表
    BlogArticleDetailVO listArticleDetail(int id); //获取文章内容及关联信息
    void removeArticleById(int id); //删除指定id文章
    void removeArticleByIdArr(List<Integer> arrId); //删除批量id文章
    void updateArticle(BlogBodyDTO blogBodyDTO); //编辑文章
    void updateArticleStatus(int id,int status); //修改文章是否可视
    void updateArticleTop(int id,int status); //修改文章是否置顶
    void updateArticleRecommend(int id,int status); //修改文章是否推荐
    void updateArticleComment(int id,int status); //修改文章是否可以评论

    BlogArticleDTO text();
}