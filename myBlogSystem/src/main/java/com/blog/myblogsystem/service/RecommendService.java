package com.blog.myblogsystem.service;

import com.blog.myblogsystem.pojo.dto.BlogBodyDTO;
import com.blog.myblogsystem.pojo.dto.BlogRecommendDTO;
import com.blog.myblogsystem.pojo.vo.BlogListRecommendVO;
import com.blog.myblogsystem.pojo.vo.BlogWalkRecommendVO;

import java.util.List;

public interface RecommendService {
    List<BlogBodyDTO> listNoRecommendlist(); //获取未被列表推荐的文章
    List<BlogListRecommendVO> listListRecommend(); //获取所有列表推荐
    List<BlogBodyDTO> listNoRecommendWalk(); //获取未被走马灯推荐的文章
    List<BlogWalkRecommendVO> listWalkRecommend(); //获取所有走马灯推荐

    void saveListRecommendById(Integer id); //新增列表推荐
    void saveWalkRecommendById(Integer id); //新增走马灯推荐

    void updateRecommendPower(Integer recommentId,Integer power); //更改权重

    void removeRecommend(Integer id,Integer recommentId,String type); //删除推荐
}
