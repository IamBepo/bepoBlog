package com.blog.myblogsystem.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.myblogsystem.enums.CommentEnum;
import com.blog.myblogsystem.mapper.BlogCommentFirstMapper;
import com.blog.myblogsystem.mapper.BlogCommentMapper;
import com.blog.myblogsystem.mapper.BlogCommentSecondMapper;
import com.blog.myblogsystem.mapper.UserInfoMapper;
import com.blog.myblogsystem.pojo.dto.BlogCommentFirstDTO;
import com.blog.myblogsystem.pojo.dto.BlogCommentSecondDTO;
import com.blog.myblogsystem.pojo.vo.BlogCommentManageVO;
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

    @Override
    public void removeCommentById(BlogCommentManageVO blogCommentManageVO) {
        if(blogCommentManageVO.getType().equals(CommentEnum.COMMENT_FIRST.name())){
            commentFirstMapper.deleteById(blogCommentManageVO.getId());
        }else {
            commentSecondMapper.deleteById(blogCommentManageVO.getId());
        }
    }

    @Override
    public void removeCommentByArr(List<BlogCommentManageVO> arr) {
        arr.forEach(item -> {
            if(item.getType().equals(CommentEnum.COMMENT_FIRST.name())){
                commentFirstMapper.deleteById(item.getId());
            }else {
                commentSecondMapper.deleteById(item.getId());
            }
        });
    }

    @Override
    public List<BlogCommentManageVO> listComment() {
        List<BlogCommentManageVO> list = new ArrayList<>();
        List<BlogCommentManageVO> firstList = commentMapper.listFirstComent();
        firstList.forEach(item -> {
            item.setType(CommentEnum.COMMENT_FIRST.name());
            list.add(item);
            List<BlogCommentManageVO> secondList = commentMapper.listSecondCommentByFirstId(item.getId());
            secondList.forEach(sItem -> {
                sItem.setType(CommentEnum.COMMENT_SECOND.name());
                sItem.setBodyId(item.getBodyId());
                sItem.setArticleId(item.getArticleId());
                sItem.setTitle(item.getTitle());
                list.add(sItem);
            });
        });
        return list;
    }
}
