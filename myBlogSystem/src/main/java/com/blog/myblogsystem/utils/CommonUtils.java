package com.blog.myblogsystem.utils;

import com.blog.myblogsystem.pojo.vo.BlogRouterVO;

import java.util.List;
import java.util.stream.Collectors;

public class CommonUtils {
    public static List<BlogRouterVO> makeRouter(List<BlogRouterVO> list) {
        List<BlogRouterVO> father = list.stream()
                .filter(item -> item.getFatherId() == 0)
                .map(item -> {
                    item.setNextRouter(getChildRouter(item,list));
                    return item;
                })
                .collect(Collectors.toList());

        return father;
    }

    public static List<BlogRouterVO> getChildRouter(BlogRouterVO father , List<BlogRouterVO> allRouter) {
        List<BlogRouterVO> children = allRouter.stream()
                .filter(item -> item.getFatherId() == father.getId())
                .map(item -> {
                    item.setNextRouter(getChildRouter(item, allRouter));
                    return item;
                })
                .collect(Collectors.toList());
        return children;
    }
}
