package com.blog.myblogsystem.web;

import com.blog.myblogsystem.constants.CodeConstants;
import com.blog.myblogsystem.constants.MsgConstants;
import com.blog.myblogsystem.pojo.dto.BlogBodyDTO;
import com.blog.myblogsystem.pojo.vo.BlogListRecommendVO;
import com.blog.myblogsystem.pojo.vo.BlogWalkRecommendVO;
import com.blog.myblogsystem.result.JsonResult;
import com.blog.myblogsystem.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/recommend")
public class RecommendController {
    @Autowired
    private RecommendService recommendService;

    /**
     * 获取未被列表推荐的文章
     * @return
     */
    @RequestMapping("/list/no/list")
    public JsonResult<List<BlogBodyDTO>> listNoRecommendlist(){
        return new JsonResult<>(recommendService.listNoRecommendlist(), CodeConstants.CODE_200);
    }

    /**
     * 新增列表推荐
     * @param id
     * @return
     */
    @RequestMapping("/save/list/{id}")
    public JsonResult saveListRecommendById(@PathVariable("id") Integer id){
        recommendService.saveListRecommendById(id);
        return new JsonResult(CodeConstants.CODE_200, MsgConstants.SUCCESS);
    }

    /**
     * 获取所有列表推荐
     * @return
     */
    @RequestMapping("/list/all/list")
    public JsonResult<List<BlogListRecommendVO>> listListRecommend(){
        return new JsonResult<>(recommendService.listListRecommend(),CodeConstants.CODE_200);
    }

    /**
     * 更新权重
     * @param recommentId
     * @param power
     * @return
     */
    @RequestMapping("/update/power")
    public JsonResult updateRecommendPower(Integer recommentId, Integer power){
        recommendService.updateRecommendPower(recommentId,power);
        return new JsonResult(CodeConstants.CODE_200, MsgConstants.SUCCESS);
    }

    /**
     * 删除推荐
     * @param id
     * @param recommentId
     * @param type
     * @return
     */
    @RequestMapping("/remove")
    public JsonResult removeRecommend(Integer id,Integer recommentId, String type){
        recommendService.removeRecommend(id,recommentId,type);
        return new JsonResult(CodeConstants.CODE_200, MsgConstants.SUCCESS);
    }

    /**
     * 获取未被走马灯推荐的文章
     * @return
     */
    @RequestMapping("/list/no/walk")
    public JsonResult<List<BlogBodyDTO>> listNoRecommendWalk(){
        return new JsonResult<>(recommendService.listNoRecommendWalk(),CodeConstants.CODE_200);
    }

    /**
     * 获取所有走马灯推荐
     * @return
     */
    @RequestMapping("/list/all/walk")
    public JsonResult<List<BlogWalkRecommendVO>> listWalkRecommend(){
        return new JsonResult<>(recommendService.listWalkRecommend(),CodeConstants.CODE_200);
    }

    /**
     * 新增走马灯推荐
     * @param id
     * @return
     */
    @RequestMapping("/save/walk/{id}")
    public JsonResult saveWalkRecommendById(@PathVariable("id") Integer id){
        recommendService.saveWalkRecommendById(id);
        return new JsonResult(CodeConstants.CODE_200, MsgConstants.SUCCESS);
    }
}
