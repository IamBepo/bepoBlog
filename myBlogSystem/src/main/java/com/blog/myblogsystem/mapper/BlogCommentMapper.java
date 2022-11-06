package com.blog.myblogsystem.mapper;

import com.blog.myblogsystem.pojo.dto.BlogCommentSecondDTO;
import com.blog.myblogsystem.pojo.vo.BlogCommentManageVO;
import com.blog.myblogsystem.pojo.vo.BlogCommentVO;
import com.blog.myblogsystem.pojo.vo.BlogNewCommentVO;
import com.blog.myblogsystem.pojo.vo.BlogSecondCommentVO;

import java.util.List;

public interface BlogCommentMapper {
    List<BlogCommentVO> getFirstCommentByArticleId(Integer id); //获取Id文章一级评论
    List<BlogSecondCommentVO> getSecondCommentByFirstId(Integer id); //获取Id评论对应二级评论

    List<BlogNewCommentVO> listNewComment(); //获取新评论
    List<BlogNewCommentVO> listNewComment2(); //获取新评论
    List<BlogCommentManageVO> listFirstComent(); //获取所有一级评论
    List<BlogCommentManageVO> listSecondCommentByFirstId(Integer id); //根据一级评论id获取所有二级评论
}
