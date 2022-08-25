package com.blog.myblogsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.myblogsystem.constants.CodeConstants;
import com.blog.myblogsystem.exception.DbException;
import com.blog.myblogsystem.mapper.*;
import com.blog.myblogsystem.pojo.dto.*;
import com.blog.myblogsystem.pojo.vo.BlogAritcleHomeVO;
import com.blog.myblogsystem.pojo.vo.BlogArticleDetailVO;
import com.blog.myblogsystem.pojo.vo.BlogArticleManageVO;
import com.blog.myblogsystem.service.ArticleService;
import com.blog.myblogsystem.utils.DateUtil;
import com.blog.myblogsystem.utils.SnowFlakeUtils;
import com.blog.myblogsystem.utils.UploadImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired(required = false)
    private BlogTabMapper blogTabMapper;

    @Autowired(required = false)
    private BlogSortMapper blogSortMapper;

    @Autowired(required = false)
    private BlogCoverMapper blogCoverMapper;

    @Autowired(required = false)
    private BlogBodyMapper blogBodyMapper;

    @Autowired(required = false)
    private BlogArticleMapper blogArticleMapper;

    @Autowired
    private SnowFlakeUtils snowFlakeUtils;

    @Override
    public List<BlogTabDTO> listBlogTab() {
        QueryWrapper<BlogTabDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return blogTabMapper.selectList(queryWrapper);
    }

    @Override
    public List<BlogTabDTO> listBlogTabBySearch(String content) {
        QueryWrapper<BlogTabDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",content);
        return blogTabMapper.selectList(queryWrapper);
    }

    @Override
    public Integer saveBlogTab(String name) {
        Timestamp currentTime = DateUtil.getCurrentTime();
        BlogTabDTO tab = new BlogTabDTO(name,currentTime);
        if(blogTabMapper.insert(tab) != 1) {
            throw new DbException("添加标签失败(Failed to insert data)", CodeConstants.CODE_500);
        }
        return 1;
    }

    @Override
    public Integer updateBlogTabStatus(int id,int status) {
        if(blogTabMapper.updateById(new BlogTabDTO(id,DateUtil.getCurrentTime(),status)) != 1 ){
            throw new DbException("修改标签失败(Failed to update data)",CodeConstants.CODE_500);
        }
        return 1;
    }

    @Override
    public Integer updateBlogTabName(int id, String newName) {
        if(blogTabMapper.updateById(new BlogTabDTO(id,newName,DateUtil.getCurrentTime())) != 1){
            throw new DbException("修改标签失败(Failed to update data)",CodeConstants.CODE_500);
        }
        return 1;
    }

    @Override
    public Integer removeBlogTab(int id) {
        if(blogTabMapper.deleteById(id) != 1){
            throw new DbException("删除标签失败(Failed to delete data)",CodeConstants.CODE_500);
        }
        return 1;
    }

    @Override
    public List<BlogSortDTO> listBlogSort() {
        QueryWrapper<BlogSortDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return blogSortMapper.selectList(queryWrapper);
    }

    @Override
    public List<BlogSortDTO> listBlogSortBySearch(String content) {
        QueryWrapper<BlogSortDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",content);
        return blogSortMapper.selectList(queryWrapper);
    }

    @Override
    public void saveBlogSort(String name) {
        int result = blogSortMapper.insert(new BlogSortDTO(name,DateUtil.getCurrentTime()));
        if(result != 1){
            throw new DbException("新增分类失败(Failed to insert data)",CodeConstants.CODE_500);
        }
    }

    @Override
    public void updateBlogSortStatus(int id, int status) {
        int result = blogSortMapper.updateById(new BlogSortDTO(id,DateUtil.getCurrentTime(),status));
        if(result != 1) {
            throw new DbException("修改分类状态失败(Failed to update data)",CodeConstants.CODE_500);
        }
    }

    @Override
    public void updateBlogSortName(int id, String newName) {
        int result = blogSortMapper.updateById(new BlogSortDTO(id,newName,DateUtil.getCurrentTime()));
        if(result != 1) {
            throw new DbException("修改分类失败(Failed to update data)",CodeConstants.CODE_500);
        }
    }

    @Override
    public void removeBlogSort(int id) {
        int result = blogSortMapper.deleteById(id);
        if(result != 1) {
            throw new DbException("删除分类失败(Failed to delete data)",CodeConstants.CODE_500);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCoverImg(MultipartFile file) {
        List<MultipartFile> list = new ArrayList<>();
        list.add(file);
        List<String> urlList = UploadImageUtils.upload(list, false);
        for(String item:urlList){
            blogCoverMapper.insert(new BlogCoverDTO(item));
        }
    }

    @Override
    public List<BlogCoverDTO> listBlogCover() {
        QueryWrapper<BlogCoverDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return blogCoverMapper.selectList(queryWrapper);
    }

    @Override
    public void removeBlogCover(int id) {
        blogCoverMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveArticle(BlogBodyDTO blogBodyDTO,String content) {
        Timestamp currentTime = DateUtil.getCurrentTime();
        blogBodyDTO.setUpdateTime(currentTime);

        Long id = snowFlakeUtils.nextId();
        blogBodyDTO.setContentId(id);
        blogBodyMapper.insert(blogBodyDTO);
        blogArticleMapper.insert(new BlogArticleDTO(id,content));
    }

    @Override
    public List<BlogArticleManageVO> listAritcleManage() {
        return blogBodyMapper.listArticleManage();
    }

    @Override
    public List<BlogArticleManageVO> listArticleBySearchTitle(String content) {
        return blogBodyMapper.listArticleManageBySearch(content);
    }

    @Override
    public void removeArticleById(int id) {
        blogBodyMapper.deleteById(id);
    }

    @Override
    public void removeArticleByIdArr(List<Integer> arrId) {
        blogBodyMapper.deleteBatchIds(arrId);
    }

    @Override
    public void updateArticle(BlogBodyDTO blogBodyDTO) {
        blogBodyMapper.updateById(blogBodyDTO);
    }

    @Override
    public void updateArticleStatus(int id, int status) {
        blogBodyMapper.updateArticleStatus(id,status);
    }

    @Override
    public void updateArticleTop(int id, int status) {
        blogBodyMapper.updateArticleTop(id,status);
    }

    @Override
    public void updateArticleRecommend(int id, int status) {
        blogBodyMapper.updateArticleRecommend(id,status);
    }

    @Override
    public void updateArticleComment(int id, int status) {
        blogBodyMapper.updateArticleComment(id,status);
    }

    @Override
    public BlogArticleDTO text() {
        return blogArticleMapper.selectById(1561593854329753600L);
    }

    @Override
    public List<BlogAritcleHomeVO> listArticleHome() {
        List<BlogAritcleHomeVO> list = blogBodyMapper.listArticleHomeBaseInfo();
        for(BlogAritcleHomeVO item : list){
            String[] split = item.getBlogTabId().split(",");
            List<Integer> tabId = new ArrayList<>();
            for(String sItem : split){
                tabId.add(Integer.parseInt(sItem));
            }
            QueryWrapper<BlogTabDTO> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("id",tabId);
            item.setBlogTab(blogTabMapper.selectList(queryWrapper));
        }

        return list;
    }

    @Override
    public BlogArticleDetailVO listArticleDetail(int id) {
        BlogArticleDetailVO blog = blogBodyMapper.listArticleDetail(id);
        blog.setArticle(blogArticleMapper.selectById(blog.getContentId()));
        String[] split = blog.getBlogTabId().split(",");
        List<Integer> tabId = new ArrayList<>();
        for(String sItem : split){
            tabId.add(Integer.parseInt(sItem));
        }
        QueryWrapper<BlogTabDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",tabId);
        blog.setBlogTab(blogTabMapper.selectList(queryWrapper));
        return blog;
    }
}
