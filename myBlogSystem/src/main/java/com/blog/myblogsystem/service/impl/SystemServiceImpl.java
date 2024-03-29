package com.blog.myblogsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.myblogsystem.mapper.BlogShieldsMapper;
import com.blog.myblogsystem.mapper.SystemFriendUrlMapper;
import com.blog.myblogsystem.mapper.SystemInfoMapper;
import com.blog.myblogsystem.pojo.dto.BlogShieldsDTO;
import com.blog.myblogsystem.pojo.dto.SystemFriendUrlDTO;
import com.blog.myblogsystem.pojo.dto.SystemInfoDTO;
import com.blog.myblogsystem.service.SystemSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
public class SystemServiceImpl implements SystemSerivice {
    @Autowired(required = false)
    private SystemInfoMapper systemInfoMapper;

    @Autowired(required = false)
    private BlogShieldsMapper blogShieldsMapper;

    @Autowired(required = false)
    private SystemFriendUrlMapper systemFriendUrlMapper;

    @Override
    public SystemInfoDTO getSystemInfo() {
        return systemInfoMapper.selectById(1);
    }

    @Override
    public List<BlogShieldsDTO> listShields() {
        return blogShieldsMapper.selectList(null);
    }

    @Override
    public void updateSystemInfo(SystemInfoDTO systemInfoDTO) {
        systemInfoDTO.setId(1);
        systemInfoMapper.updateById(systemInfoDTO);
    }

    @Override
    public void updateShields(List<BlogShieldsDTO> list) {
        list.forEach(item -> {
            blogShieldsMapper.updateById(item);
        });
    }

    @Override
    public List<SystemFriendUrlDTO> listFriendUrl() {
        QueryWrapper<SystemFriendUrlDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return systemFriendUrlMapper.selectList(queryWrapper);
    }

    @Override
    public void saveFriendUrl(SystemFriendUrlDTO systemFriendUrlDTO) {
        systemFriendUrlMapper.insert(systemFriendUrlDTO);
    }

    @Override
    public void removeFriendUrl(Integer id) {
        systemFriendUrlMapper.deleteById(id);
    }
}
