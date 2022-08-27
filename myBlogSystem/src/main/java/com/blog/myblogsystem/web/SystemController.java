package com.blog.myblogsystem.web;

import com.blog.myblogsystem.constants.CodeConstants;
import com.blog.myblogsystem.constants.MsgConstants;
import com.blog.myblogsystem.pojo.dto.BlogShieldsDTO;
import com.blog.myblogsystem.pojo.dto.SystemInfoDTO;
import com.blog.myblogsystem.result.JsonResult;
import com.blog.myblogsystem.service.SystemSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/system")
public class SystemController {
    @Autowired
    private SystemSerivice systemSerivice;

    /**
     * 获取网站基本信息
     * @return
     */
    @RequestMapping("/get/info")
    public JsonResult<SystemInfoDTO> getSystemInfo() {
        return new JsonResult<>(systemSerivice.getSystemInfo(), CodeConstants.CODE_200);
    }

    /**
     * 更新网站基本信息
     * @param systemInfoDTO
     */
    @RequestMapping("/update/info")
    public JsonResult updateSystemInfo(SystemInfoDTO systemInfoDTO){
        systemSerivice.updateSystemInfo(systemInfoDTO);
        return new JsonResult(CodeConstants.CODE_200, MsgConstants.SUCCESS);
    }

    /**
     * 获取全部盾牌信息
     * @return
     */
    @RequestMapping("/list/shields")
    public JsonResult<List<BlogShieldsDTO>> listShields(){
        return new JsonResult<>(systemSerivice.listShields(), CodeConstants.CODE_200);
    }

    /**
     * 更新盾牌信息
     * @param list
     */
    @RequestMapping("/update/shields")
    public JsonResult updateShields(@RequestBody List<BlogShieldsDTO> list){
        systemSerivice.updateShields(list);
        return new JsonResult(CodeConstants.CODE_200, MsgConstants.SUCCESS);
    }
}
