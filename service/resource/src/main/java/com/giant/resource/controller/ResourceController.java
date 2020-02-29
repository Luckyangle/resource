package com.giant.resource.controller;

import com.giant.resource.entity.FileData;
import com.giant.resource.query.FileDataQuery;
import com.giant.resource.entity.FileUser;
import com.giant.resource.service.FileDataService;
import com.giant.resource.service.FileUserService;
import com.giantan.common.constant.ResultConstant;
import com.giantan.common.utils.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Lvzhihang
 * 2020/2/27
 * defination：
 */
@RestController
@RequestMapping("/resource")
@AllArgsConstructor
@Api("文件模块")
public class ResourceController {

    private FileDataService fileDataService;

    private FileUserService fileUserService;

    @GetMapping("/file-list")
    @ApiOperation("获取文件列表")
    @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "String")
    public R<List<FileData>> getFileList(@RequestParam String userId){
        final List<FileData> fileDataList = fileDataService.getFileDataList(userId)
                .stream().sorted((f1, f2) -> f1.getCreateTime().compareTo(f2.getCreateTime()) < 0 ? 1 : -1)
                .collect(Collectors.toList());
        return R.data(fileDataList);
    }

    @GetMapping("/getFile")
    @ApiOperation("获取某个文件的详情")
    @ApiImplicitParam(name = "fileId", value = "文件ID", dataType = "String")
    public R<FileData> getFileMetadata(@RequestParam String fileId){
        FileData fileData = fileDataService.selectOne(fileId);
        return R.data(fileData);
    }

    @GetMapping("/search")
    @ApiOperation("搜搜文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "String"),
            @ApiImplicitParam(name = "fileName", value = "文件名称", dataType = "String"),
            @ApiImplicitParam(name = "fileGroup", value = "文件分类", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String")

    })
    public R<List<FileData>> searchFile(@RequestParam String userId,
                                        @RequestParam(required = false) String fileName,
                                        @RequestParam(required = false) String fileGroup,
                                        @RequestParam(required = false) String startTime,
                                        @RequestParam(required = false) String endTime){
        FileDataQuery query = FileDataQuery.builder()
                .userId(userId)
                .fileName(fileName)
                .fileGroup(fileGroup)
                .startTime((startTime == null || "".equals(startTime)) ? null : LocalDateTime.parse(startTime))
                .endTime((endTime == null || "".equals(endTime))? null : LocalDateTime.parse(endTime))
                .build();
        List<FileData> searchResult = fileDataService.searchFile(query);
        return R.data(searchResult);
    }

    @PostMapping("/create-file")
    @ApiOperation("创建文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "上传的文件", dataType = "File"),
            @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "String")

    })
    public R<ResultConstant> createFile(FileData fileData,
                                @RequestParam(required = false) MultipartFile file,
                                @RequestParam(required = false) String  userId){
        if (!fileDataService.isNameExist(userId, fileData.getFileName())){
            if (file != null){
                String content = FileUtil.receiveFromMultipartFile(file);
                fileData.setContent(content);
            }
            String fileId = UUID.randomUUID().toString();
            fileData.setFileId(fileId);
            fileData.setCreateTime(LocalDateTime.now());
            fileData.setUpdateTime(LocalDateTime.now());
            fileDataService.insert(fileData);
            fileUserService.insert(new FileUser(fileId, userId));
            return R.data(ResultConstant.SUCCESS);
        }
        return R.fail(ResultConstant.FILE_NAME_EXIST);
    }

    @PutMapping("update-file")
    @ApiOperation("更新文件信息")
    @ApiImplicitParam(name = "userId", value = "用户名称", dataType = "String")
    public R<ResultConstant> updateFile(FileData fileData, @RequestParam String userId){
        FileData previous = fileDataService.selectOne(fileData.getFileId());
        if (!previous.getFileName().equals(fileData.getFileName()) &&
                fileDataService.isNameExist(userId, fileData.getFileName())){
            return R.fail(ResultConstant.FILE_NAME_EXIST);
        }
        fileData.setUpdateTime(LocalDateTime.now());
        fileData.setCreateTime(previous.getCreateTime());
        fileDataService.update(fileData);
        return R.data(ResultConstant.SUCCESS);
    }

    @DeleteMapping("/delete-file")
    @ApiOperation("删除某个文件")
    @ApiImplicitParam(name = "fileId", value = "文件ID", dataType = "String")
    public R<ResultConstant> deleteFile(@RequestParam String fileId){
        fileDataService.delete(fileId);
        return R.data(ResultConstant.SUCCESS);
    }
}

