package com.blog.myblogsystem.web;

import com.blog.myblogsystem.constants.CodeConstants;
import com.blog.myblogsystem.constants.MsgConstants;
import com.blog.myblogsystem.result.JsonResult;
import com.blog.myblogsystem.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UploadController {
    @Autowired
    private ArticleService articleService;

    /**
     * 上传封面图片
     * @param file
     * @return
     */
    @RequestMapping("/upload/img")
    public JsonResult uploadImg(MultipartFile file){
        articleService.saveCoverImg(file);
        return new JsonResult(CodeConstants.CODE_200, MsgConstants.SUCCESS);
    }
}
