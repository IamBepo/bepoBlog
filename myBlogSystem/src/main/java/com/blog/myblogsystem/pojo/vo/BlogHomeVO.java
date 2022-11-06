package com.blog.myblogsystem.pojo.vo;

import com.blog.myblogsystem.pojo.dto.BlogShieldsDTO;
import com.blog.myblogsystem.pojo.dto.BlogTabDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogHomeVO {
    private List<BlogAritcleHomeVO> article;
    private List<BlogTabDTO> tab;
    private List<BlogNewCommentVO> newComment;
    private List<BlogListRecommendVO> listRecommend;
    private List<BlogWalkRecommendVO> walkRecommend;
    private List<BlogShieldsDTO> shields;
}
