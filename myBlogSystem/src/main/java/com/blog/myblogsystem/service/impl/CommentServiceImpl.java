package com.blog.myblogsystem.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.myblogsystem.mapper.BlogCommentFirstMapper;
import com.blog.myblogsystem.mapper.BlogCommentMapper;
import com.blog.myblogsystem.mapper.BlogCommentSecondMapper;
import com.blog.myblogsystem.mapper.UserInfoMapper;
import com.blog.myblogsystem.pojo.dto.BlogCommentFirstDTO;
import com.blog.myblogsystem.pojo.dto.BlogCommentSecondDTO;
import com.blog.myblogsystem.pojo.vo.BlogCommentVO;
import com.blog.myblogsystem.pojo.vo.BlogNewCommentVO;
import com.blog.myblogsystem.pojo.vo.BlogSecondCommentVO;
import com.blog.myblogsystem.service.CommentService;
import com.blog.myblogsystem.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired(required = false)
    private BlogCommentFirstMapper commentFirstMapper;

    @Autowired(required = false)
    private BlogCommentSecondMapper commentSecondMapper;

    @Autowired(required = false)
    private BlogCommentMapper commentMapper;

    @Autowired(required = false)
    private UserInfoMapper userInfoMapper;

    @Override
    public void saveFirstComment(BlogCommentFirstDTO blogCommentFirstDTO) {
        blogCommentFirstDTO.setUpdateTime(DateUtil.getCurrentTime());
        commentFirstMapper.insert(blogCommentFirstDTO);
    }

    @Override
    public void saveSecondComment(BlogCommentSecondDTO blogCommentSecondDTO) {
        blogCommentSecondDTO.setUpdateTime(DateUtil.getCurrentTime());
        commentSecondMapper.insert(blogCommentSecondDTO);
    }


    @Override
    public List<BlogCommentVO> listCommentByArticleId(Integer id) {
        List<BlogCommentVO> list = commentMapper.getFirstCommentByArticleId(id);
        list.stream()
                .forEach(blogComment -> {
                    blogComment.setUserInfo(userInfoMapper.selectById(blogComment.getUserId()));
                    List<BlogSecondCommentVO> secondList = commentMapper.getSecondCommentByFirstId(blogComment.getId());
                    secondList.forEach(blogSecondComment -> {
                        blogSecondComment.setUserInfo(userInfoMapper.selectById(blogSecondComment.getUserId()));
                        blogSecondComment.setReplyUserInfo(userInfoMapper.selectById(blogSecondComment.getReplyUserId()));
                    });
                    blogComment.setSecondComment(secondList);
                });
        return list;
    }

    @Override
    public List<BlogNewCommentVO> listNewComment() {
        List<BlogNewCommentVO> list = commentMapper.listNewComment();
        list.forEach(item -> {
            item.setUserInfo(userInfoMapper.selectById(item.getUserId()));
        });
        return list;
    }
}
