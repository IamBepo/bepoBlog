package com.blog.myblogsystem.service;

import com.blog.myblogsystem.pojo.vo.BlogHomeVO;
import org.springframework.stereotype.Service;

public interface AggregationService {
    /**
     * 聚合首页展示
     */
    BlogHomeVO listHome();
}
