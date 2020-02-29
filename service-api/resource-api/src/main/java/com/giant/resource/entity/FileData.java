package com.giant.resource.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import rx.BackpressureOverflow;

import java.time.LocalDateTime;
import java.util.Date;

import static com.baomidou.mybatisplus.annotation.FieldStrategy.NOT_NULL;

/**
 * Created by Lvzhihang
 * 2020/2/27
 * defination：
 */
@Data
@NoArgsConstructor
@TableName("file_data")
@ApiModel(value = "FileData", description = "文件对象")
public class FileData {
    @TableId
    @ApiParam
    private String fileId;

    @ApiParam
    private String fileName;

    @ApiParam
    private String filePath;

    @ApiParam
    private String fileGroup;

    @ApiParam
    private String content;

    @ApiParam
    private String fileDescription;

    @ApiParam
    @TableField(updateStrategy = NOT_NULL)
    private LocalDateTime createTime;

    @ApiParam
    private LocalDateTime updateTime;
}
