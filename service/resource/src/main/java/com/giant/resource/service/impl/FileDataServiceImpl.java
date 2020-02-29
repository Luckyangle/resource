package com.giant.resource.service.impl;

//import com.baomidou.mybatisplus.mapper.EntityWrapper;
//import com.baomidou.mybatisplus.mapper.Wrapper;
//import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.giant.resource.entity.FileData;
import com.giant.resource.query.FileDataQuery;
import com.giant.resource.entity.FileUser;
import com.giant.resource.mapper.FileDataMapper;
import com.giant.resource.mapper.FileUserMapper;
import com.giant.resource.service.FileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Lvzhihang
 * 2020/2/28
 * defination：
 */
@Service
public class FileDataServiceImpl implements FileDataService{

    @Autowired
    private FileDataMapper fileDataMapper;

    @Autowired
    private FileUserMapper fileUserMapper;

    @Override
    public FileData selectOne(String fileId) {
        return fileDataMapper.selectById(fileId);
    }

    @Override
    public List<FileData> getFileDataList(String userId) {
        QueryWrapper<FileUser> queryWrapper1 = new QueryWrapper<FileUser>().eq("user_id", userId);
        List<FileUser> fileUsers = fileUserMapper.selectList(queryWrapper1);
        List<String> userIds = fileUsers.stream().map(fileUser -> fileUser.getFileId())
                .collect(Collectors.toList());
        QueryWrapper<FileData> queryWrapper2 = new QueryWrapper<FileData>().in("file_id", userIds);
        return fileDataMapper.selectList(queryWrapper2);
    }

    @Override
    public List<FileData> searchFile(FileDataQuery query) {
        return fileDataMapper.search(query);
    }

    @Override
    public void insert(FileData fileData) {
        fileDataMapper.insert(fileData);
    }

    @Override
    public void update(FileData fileData) {

        fileDataMapper.updateById(fileData);
    }

    @Override
    public void delete(String fileId) {
        fileDataMapper.deleteById(fileId);
    }

    @Override
    public boolean isNameExist(String userId, String fileName) {
        List<FileData> fileDataList = this.getFileDataList(userId);
        if((fileDataList.stream()
                .filter(fileData -> fileData.getFileName().equals(fileName))
                .findFirst().isPresent())){
            return true;
        }
        return false;
    }
}
