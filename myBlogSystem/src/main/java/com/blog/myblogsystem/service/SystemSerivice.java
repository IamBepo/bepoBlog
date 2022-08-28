package com.blog.myblogsystem.service;

import com.blog.myblogsystem.pojo.dto.BlogShieldsDTO;
import com.blog.myblogsystem.pojo.dto.SystemFriendUrlDTO;
import com.blog.myblogsystem.pojo.dto.SystemInfoDTO;

import java.util.List;

public interface SystemSerivice {
    SystemInfoDTO getSystemInfo(); //获取网站基本信息
    List<BlogShieldsDTO> listShields(); //获取网站盾牌
    List<SystemFriendUrlDTO> listFriendUrl(); //获取所有友情链接

    void updateSystemInfo(SystemInfoDTO systemInfoDTO); //更新网站基本信息
    void updateShields(List<BlogShieldsDTO> list); //更新盾牌信息
    void saveFriendUrl(SystemFriendUrlDTO systemFriendUrlDTO); //新增友情链接
    void removeFriendUrl(Integer id); //删除友情链接
}
