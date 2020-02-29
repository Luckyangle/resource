package com.giant.resource.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Lvzhihang
 * 2020/2/28
 * defination：
 */
@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@TableName("file_user")
public class FileUser {
    private String fileId;
    private String userId;
}
