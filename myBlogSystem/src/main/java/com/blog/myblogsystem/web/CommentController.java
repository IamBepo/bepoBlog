package com.blog.myblogsystem.web;

import com.blog.myblogsystem.constants.CodeConstants;
import com.blog.myblogsystem.constants.MsgConstants;
import com.blog.myblogsystem.pojo.dto.BlogCommentFirstDTO;
import com.blog.myblogsystem.pojo.dto.BlogCommentSecondDTO;
import com.blog.myblogsystem.pojo.vo.BlogCommentVO;
import com.blog.myblogsystem.pojo.vo.BlogNewCommentVO;
import com.blog.myblogsystem.result.JsonResult;
import com.blog.myblogsystem.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping("/save/first")
    public JsonResult saveFirstComment(BlogCommentFirstDTO blogCommentFirstDTO){
        commentService.saveFirstComment(blogCommentFirstDTO);
        return new JsonResult(CodeConstants.CODE_200, MsgConstants.SUCCESS);
    }

    @RequestMapping("/save/second")
    public JsonResult saveSecondComment(BlogCommentSecondDTO blogCommentSecondDTO){
        commentService.saveSecondComment(blogCommentSecondDTO);
        return new JsonResult(CodeConstants.CODE_200, MsgConstants.SUCCESS);
    }

    @RequestMapping("/list/{id}")
    public JsonResult<List<BlogCommentVO>> listCommentByArticleId(@PathVariable("id") Integer id){
        return new JsonResult<>(commentService.listCommentByArticleId(id),CodeConstants.CODE_200);
    }

    @RequestMapping("/list/new")
    public JsonResult<List<BlogNewCommentVO>> listNewComment(){
        return new JsonResult<>(commentService.listNewComment(),CodeConstants.CODE_200);
    }
}
