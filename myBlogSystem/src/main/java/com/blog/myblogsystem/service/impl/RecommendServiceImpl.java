package com.blog.myblogsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.myblogsystem.constants.RecommendConstants;
import com.blog.myblogsystem.enums.RecommendEnum;
import com.blog.myblogsystem.mapper.BlogBodyMapper;
import com.blog.myblogsystem.mapper.BlogRecommendMapper;
import com.blog.myblogsystem.pojo.dto.BlogBodyDTO;
import com.blog.myblogsystem.pojo.dto.BlogRecommendDTO;
import com.blog.myblogsystem.pojo.vo.BlogListRecommendVO;
import com.blog.myblogsystem.pojo.vo.BlogWalkRecommendVO;
import com.blog.myblogsystem.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {
    @Autowired(required = false)
    private BlogRecommendMapper blogRecommendMapper;
    @Autowired(required = false)
    private BlogBodyMapper blogBodyMapper;

    @Override
    public List<BlogBodyDTO> listNoRecommendlist() {
        QueryWrapper<BlogBodyDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("recommend_status",0)
                .or()
                .eq("recommend_status", RecommendEnum.ONLY_RECOMMEND_WALK.getType())
                .orderByDesc("id");
        return blogBodyMapper.selectList(queryWrapper);
    }

    @Override
    public void saveListRecommendById(Integer id) {
        BlogBodyDTO blogBodyDTO = blogBodyMapper.selectById(id);
        blogBodyMapper.updateArticleRecommend(id,RecommendEnum.ONLY_RECOMMEND_LIST.getType() + blogBodyDTO.getRecommendStatus());
        blogRecommendMapper.insert(new BlogRecommendDTO(id,
                RecommendEnum.ONLY_RECOMMEND_LIST.getType(),
                RecommendEnum.ONLY_RECOMMEND_LIST.name(),
                RecommendConstants.DEFAULT_POWER));
    }

    @Override
    public List<BlogListRecommendVO> listListRecommend() {
        return blogRecommendMapper.listListRecommend();
    }

    @Override
    public void updateRecommendPower(Integer recommentId, Integer power) {
        blogRecommendMapper.updateById(new BlogRecommendDTO(recommentId,power));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeRecommend(Integer id,Integer recommentId, String type) {
        blogRecommendMapper.deleteById(recommentId);
        BlogBodyDTO blogBodyDTO = blogBodyMapper.selectById(id);
        if(type.equals(RecommendEnum.ONLY_RECOMMEND_LIST.getName())){
            if(blogBodyDTO.getRecommendStatus() - RecommendEnum.ONLY_RECOMMEND_LIST.getType() < 0){
                blogBodyDTO.setRecommendStatus(0);
            }else{
                blogBodyDTO.setRecommendStatus(blogBodyDTO.getRecommendStatus() - RecommendEnum.ONLY_RECOMMEND_LIST.getType());
            }
        }else if(type.equals(RecommendEnum.ONLY_RECOMMEND_WALK.getName())){
            if(blogBodyDTO.getRecommendStatus() - RecommendEnum.ONLY_RECOMMEND_WALK.getType() < 0){
                blogBodyDTO.setRecommendStatus(0);
            }else{
                blogBodyDTO.setRecommendStatus(blogBodyDTO.getRecommendStatus() - RecommendEnum.ONLY_RECOMMEND_WALK.getType());
            }
        }
        blogBodyMapper.updateById(blogBodyDTO);
    }

    @Override
    public List<BlogBodyDTO> listNoRecommendWalk() {
        QueryWrapper<BlogBodyDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("recommend_status",0)
                .or()
                .eq("recommend_status", RecommendEnum.ONLY_RECOMMEND_LIST.getType())
                .orderByDesc("id");
        return blogBodyMapper.selectList(queryWrapper);
    }

    @Override
    public List<BlogWalkRecommendVO> listWalkRecommend() {
        return blogRecommendMapper.listWalkRecommend();
    }

    @Override
    public void saveWalkRecommendById(Integer id) {
        BlogBodyDTO blogBodyDTO = blogBodyMapper.selectById(id);
        blogBodyMapper.updateArticleRecommend(id,RecommendEnum.ONLY_RECOMMEND_WALK.getType() + blogBodyDTO.getRecommendStatus());
        blogRecommendMapper.insert(new BlogRecommendDTO(id,
                RecommendEnum.ONLY_RECOMMEND_WALK.getType(),
                RecommendEnum.ONLY_RECOMMEND_WALK.name(),
                RecommendConstants.DEFAULT_POWER));
    }
}
