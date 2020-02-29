package com.giant.resource.service.impl;

import com.giant.resource.entity.FileUser;
import com.giant.resource.mapper.FileUserMapper;
import com.giant.resource.service.FileUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Lvzhihang
 * 2020/2/28
 * defination：
 */
@Service
public class FileUserServiceImpl implements FileUserService{
    @Autowired
    private FileUserMapper fileUserMapper;

    @Override
    public void insert(FileUser fileUser) {
        fileUserMapper.insert(fileUser);
    }
}
