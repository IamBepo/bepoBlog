package com.blog.myblogsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.myblogsystem.mapper.*;
import com.blog.myblogsystem.pojo.dto.BlogShieldsDTO;
import com.blog.myblogsystem.pojo.dto.BlogTabDTO;
import com.blog.myblogsystem.pojo.vo.*;
import com.blog.myblogsystem.service.AggregationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AggregationServiceImpl implements AggregationService {
    @Autowired(required = false)
    private BlogBodyMapper blogBodyMapper;
    @Autowired(required = false)
    private BlogTabMapper blogTabMapper;
    @Autowired(required = false)
    private BlogCommentMapper blogCommentMapper;
    @Autowired(required = false)
    private BlogRecommendMapper blogRecommendMapper;
    @Autowired(required = false)
    private BlogShieldsMapper blogShieldsMapper;

    @Override
    public BlogHomeVO listHome() {
        List<BlogAritcleHomeVO> list = blogBodyMapper.listArticleHomeBaseInfo();

        //标签处理
        List<BlogTabDTO> tabList = blogTabMapper.selectList(null);
        Map<Integer, BlogTabDTO> tabMap = tabList.stream().collect(Collectors.toMap(BlogTabDTO::getId, Function.identity()));

        //新评论处理
        List<BlogNewCommentVO> commentList = blogCommentMapper.listNewComment2();

        //推荐处理
        List<BlogWalkRecommendVO> walkRecommendList = blogRecommendMapper.listWalkRecommend();
        List<BlogListRecommendVO> listRecommendList = blogRecommendMapper.listListRecommend();

        //盾牌处理
        List<BlogShieldsDTO> shieldsList = blogShieldsMapper.selectList(null);

        list.forEach(item -> CompletableFuture.supplyAsync(() -> {
            String[] split = item.getBlogTabId().split(",");
            List<BlogTabDTO> tabTemp = new ArrayList<>();
            for(String sItem : split){
                tabTemp.add(tabMap.get(Integer.parseInt(sItem)));
            }
            item.setBlogTab(tabTemp);
            return null;
        }));

        //TODO 未查询分类
        Map<String, Integer> count = new HashMap<>(3);
        count.put("articleCount",list.size());
        count.put("sortCount",100);
        count.put("tagCount",tabList.size());

        return BlogHomeVO.builder()
                .article(list)
                .tab(tabList)
                .newComment(commentList)
                .walkRecommend(walkRecommendList)
                .listRecommend(listRecommendList)
                .shields(shieldsList)
                .count(count)
                .build();
    }
}
