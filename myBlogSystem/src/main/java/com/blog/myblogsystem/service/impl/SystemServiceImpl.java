package com.blog.myblogsystem.service.impl;

import com.blog.myblogsystem.mapper.BlogShieldsMapper;
import com.blog.myblogsystem.mapper.SystemInfoMapper;
import com.blog.myblogsystem.pojo.dto.BlogShieldsDTO;
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
}
