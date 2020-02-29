package com.giantan.common.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springblade.core.tool.api.IResultCode;

/**
 * Created by Lvzhihang
 * 2020/2/28
 * defination：
 */
@Getter
@AllArgsConstructor
public enum ResultConstant implements IResultCode {

    SUCCESS(200, "请求成功"),

    FILE_NAME_EXIST(401, "文件名称已存在");

    private int code;

    private String message;

}
