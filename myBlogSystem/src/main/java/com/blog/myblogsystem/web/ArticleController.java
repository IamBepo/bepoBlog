package com.blog.myblogsystem.web;

import com.blog.myblogsystem.constants.CodeConstants;
import com.blog.myblogsystem.constants.MsgConstants;
import com.blog.myblogsystem.pojo.dto.BlogBodyDTO;
import com.blog.myblogsystem.pojo.dto.BlogCoverDTO;
import com.blog.myblogsystem.pojo.dto.BlogSortDTO;
import com.blog.myblogsystem.pojo.dto.BlogTabDTO;
import com.blog.myblogsystem.pojo.vo.BlogAritcleHomeVO;
import com.blog.myblogsystem.pojo.vo.BlogArticleDetailVO;
import com.blog.myblogsystem.pojo.vo.BlogArticleManageVO;
import com.blog.myblogsystem.result.JsonResult;
import com.blog.myblogsystem.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/blog")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 获取所有标签
     * @return
     */
    @RequestMapping("/tab/list")
    public JsonResult<List<BlogTabDTO>> listBlogTab(){
        List<BlogTabDTO> list = articleService.listBlogTab();
        return new JsonResult<>(list, CodeConstants.CODE_200);
    }

    /**
     * 新增标签
     * @param name
     * @return
     */
    @RequestMapping("/tab/save")
    public JsonResult saveBlogTab(String name){
        articleService.saveBlogTab(name);
        return new JsonResult(CodeConstants.CODE_200, MsgConstants.SUCCESS);
    }

    /**
     * 搜索标签
     * @param content
     * @return
     */
    @RequestMapping("/tab/search")
    public JsonResult<List<BlogTabDTO>> listBlogTabBySearch(String content){
        List<BlogTabDTO> list = articleService.listBlogTabBySearch(content);
        return new JsonResult<>(list,CodeConstants.CODE_200);
    }

    /**
     * 更新标签状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/tab/update/status")
    public JsonResult updateBlogTabStatus(int id,int status){
        articleService.updateBlogTabStatus(id,status);
        return new JsonResult(CodeConstants.CODE_200, MsgConstants.SUCCESS);
    }

    /**
     * 更新标签名
     * @param id
     * @param newName
     * @return
     */
    @RequestMapping("/tab/update/exit")
    public JsonResult updateBlogTabName(int id, String newName){
        articleService.updateBlogTabName(id,newName);
        return new JsonResult(CodeConstants.CODE_200,MsgConstants.SUCCESS);
    }

    /**
     * 删除标签
     * @param id
     * @return
     */
    @RequestMapping("/tab/delete/{id}")
    public JsonResult removeBlogTab(@PathVariable("id") Integer id){
        articleService.removeBlogTab(id);
        return new JsonResult(CodeConstants.CODE_200,MsgConstants.SUCCESS);
    }

    /**
     * 获取所有分类
     * @return
     */
    @RequestMapping("/sort/list")
    public JsonResult<List<BlogSortDTO>> listBlogSort(){
        List<BlogSortDTO> list = articleService.listBlogSort();
        return new JsonResult<>(list,CodeConstants.CODE_200);
    }

    /**
     * 搜索分类
     * @param content
     * @return
     */
    @RequestMapping("/sort/search")
    public JsonResult<List<BlogSortDTO>> listBlogSortBySearch(String content){
        List<BlogSortDTO> list = articleService.listBlogSortBySearch(content);
        return new JsonResult<>(list,CodeConstants.CODE_200);
    }

    /**
     * 新增分类
     * @param name
     * @return
     */
    @RequestMapping("/sort/save")
    public JsonResult saveBlogSort(String name){
        articleService.saveBlogSort(name);
        return new JsonResult(CodeConstants.CODE_200,MsgConstants.SUCCESS);
    }

    /**
     * 更新分类状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/sort/update/status")
    public JsonResult updateBlogSortStatus(int id, int status){
        articleService.updateBlogSortStatus(id,status);
        return new JsonResult(CodeConstants.CODE_200,MsgConstants.SUCCESS);
    }

    /**
     * 更新分类名
     * @param id
     * @param newName
     * @return
     */
    @RequestMapping("/sort/update/name")
    public JsonResult updateBlogSortName(int id, String newName){
        articleService.updateBlogSortName(id,newName);
        return new JsonResult(CodeConstants.CODE_200,MsgConstants.SUCCESS);
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @RequestMapping("/sort/remove/{id}")
    public JsonResult removeBlogSort(@PathVariable("id") int id){
        articleService.removeBlogSort(id);
        return new JsonResult(CodeConstants.CODE_200,MsgConstants.SUCCESS);
    }

    /**
     * 获取所有封面图片
     * @return
     */
    @RequestMapping("/cover/list")
    public JsonResult<List<BlogCoverDTO>> listBlogCover(){
        return new JsonResult<>(articleService.listBlogCover(),CodeConstants.CODE_200);
    }

    /**
     * 删除封面图片
     * @param id
     * @return
     */
    @RequestMapping("/cover/remove/{id}")
    public JsonResult removeBlogCover(@PathVariable("id") int id){
        articleService.removeBlogCover(id);
        return new JsonResult(CodeConstants.CODE_200,MsgConstants.SUCCESS);
    }

    /**
     * 发表文章
     * @param blogBodyDTO
     * @return
     */
    @RequestMapping("/save")
    public JsonResult saveArticle(BlogBodyDTO blogBodyDTO,String content){
        articleService.saveArticle(blogBodyDTO,content);
        return new JsonResult(CodeConstants.CODE_200,MsgConstants.SUCCESS);
    }

    /**
     * 获取所有文章
     * @return
     */
    @RequestMapping("/list")
    public JsonResult<List<BlogArticleManageVO>> listAritcle(){
        return new JsonResult<>(articleService.listAritcleManage(),CodeConstants.CODE_200);
    }

    /**
     * 获取搜索文章
     * @param content
     * @return
     */
    @RequestMapping("/search")
    public JsonResult<List<BlogArticleManageVO>> listArticleBySearchTitle(String content){
        return new JsonResult<>(articleService.listArticleBySearchTitle(content),CodeConstants.CODE_200);
    }

    /**
     * 删除id文章
     * @param id
     * @return
     */
    @RequestMapping("/remove/{id}")
    public JsonResult removeArticleById(@PathVariable("id") int id){
        articleService.removeArticleById(id);
        return new JsonResult(CodeConstants.CODE_200,MsgConstants.SUCCESS);
    }

    /**
     * 删除id群文章
     * @param arrId
     * @return
     */
    @RequestMapping("/remove/arr")
    public JsonResult removeArticleByIdArr(@RequestBody List<Integer> arrId){
        articleService.removeArticleByIdArr(arrId);
        return new JsonResult(CodeConstants.CODE_200,MsgConstants.SUCCESS);
    }

    /**
     * 编辑文章
     * @param blogBodyDTO
     * @return
     */
    @RequestMapping("/update/article")
    public JsonResult updateArticle(BlogBodyDTO blogBodyDTO){
        articleService.updateArticle(blogBodyDTO);
        return new JsonResult(CodeConstants.CODE_200,MsgConstants.SUCCESS);
    }

    /**
     * 更新文章可视状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/update/status/vs")
    public JsonResult updateArticleStatus(int id, int status){
        articleService.updateArticleStatus(id,status);
        return new JsonResult(CodeConstants.CODE_200,MsgConstants.SUCCESS);
    }

    /**
     * 更新文章置顶状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/update/status/top")
    public JsonResult updateArticleTop(int id, int status){
        articleService.updateArticleTop(id,status);
        return new JsonResult(CodeConstants.CODE_200,MsgConstants.SUCCESS);
    }

    /**
     * 更新文章推荐状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/update/status/recommend")
    public JsonResult updateArticleRecommend(int id, int status){
        articleService.updateArticleRecommend(id,status);
        return new JsonResult(CodeConstants.CODE_200,MsgConstants.SUCCESS);
    }

    /**
     * 更新文章可否评论状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/update/status/comment")
    public JsonResult updateArticleComment(int id, int status){
        articleService.updateArticleComment(id,status);
        return new JsonResult(CodeConstants.CODE_200,MsgConstants.SUCCESS);
    }

    @RequestMapping("/text")
    public JsonResult text(){
        return new JsonResult(articleService.text(),CodeConstants.CODE_200);
    }

    /**
     * 获取首页文章列表
     * @return
     */
    @RequestMapping("/list/home")
    public JsonResult<List<BlogAritcleHomeVO>> listArticleHome(){
        return new JsonResult<>(articleService.listArticleHome(),CodeConstants.CODE_200);
    }

    /**
     * 获取id文章内容
     * @param id
     * @return
     */
    @RequestMapping("/read/{id}")
    public JsonResult<BlogArticleDetailVO> listArticleDetail(@PathVariable("id") int id){
        return new JsonResult<>(articleService.listArticleDetail(id),CodeConstants.CODE_200);
    }

    /**
     * 关键字搜索文章
     * @param key
     * @return
     */
    @RequestMapping("/list/search")
    public JsonResult<List<BlogAritcleHomeVO>> listSearchArticle(String key) {
        return new JsonResult<>(articleService.listSearchArticle(key),CodeConstants.CODE_200);
    }

}
