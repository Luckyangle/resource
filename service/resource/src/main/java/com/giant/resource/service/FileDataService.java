package com.giant.resource.service;

import com.giant.resource.entity.FileData;
import com.giant.resource.query.FileDataQuery;

import java.util.List;

/**
 * Created by Lvzhihang
 * 2020/2/28
 * defination：
 */

public interface FileDataService {

    FileData selectOne(String fileId);

    List<FileData> getFileDataList(String userId);

    List<FileData> searchFile(FileDataQuery fileDataQuery);

    void insert(FileData fileData);

    void update(FileData fileData);

    void delete(String fileId);

    boolean isNameExist(String userId, String fileName);


}
