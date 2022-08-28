package com.blog.myblogsystem.service;

import com.blog.myblogsystem.pojo.dto.BlogCommentFirstDTO;
import com.blog.myblogsystem.pojo.dto.BlogCommentSecondDTO;
import com.blog.myblogsystem.pojo.vo.BlogCommentManageVO;
import com.blog.myblogsystem.pojo.vo.BlogCommentVO;
import com.blog.myblogsystem.pojo.vo.BlogNewCommentVO;

import java.util.List;

public interface CommentService {
    void saveFirstComment(BlogCommentFirstDTO blogCommentFirstDTO); //新增一级评论
    void saveSecondComment(BlogCommentSecondDTO blogCommentSecondDTO); //新增二级评论
    void removeCommentById(BlogCommentManageVO blogCommentManageVO); //删除单个评论
    void removeCommentByArr(List<BlogCommentManageVO> arr); //删除多个评论

    List<BlogCommentVO> listCommentByArticleId(Integer id); //获取id文章评论
    List<BlogNewCommentVO> listNewComment(); //获取新的评论(前N条)
    List<BlogCommentManageVO> listComment(); //获取所有评论
}
