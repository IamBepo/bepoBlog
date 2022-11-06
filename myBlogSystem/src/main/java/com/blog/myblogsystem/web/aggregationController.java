package com.blog.myblogsystem.web;

import com.blog.myblogsystem.constants.CodeConstants;
import com.blog.myblogsystem.pojo.vo.BlogHomeVO;
import com.blog.myblogsystem.result.JsonResult;
import com.blog.myblogsystem.service.AggregationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/blog")
public class aggregationController {
    @Autowired
    private AggregationService aggregationService;
    /**
     * 首页数据聚合
     */
    @RequestMapping("/home")
    public JsonResult<BlogHomeVO> listHome() {
        BlogHomeVO home = aggregationService.listHome();
        return new JsonResult<>(home, CodeConstants.CODE_200);
    }
}
