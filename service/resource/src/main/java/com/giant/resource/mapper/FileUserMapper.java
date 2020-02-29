package com.giant.resource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.giant.resource.entity.FileUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by Lvzhihang
 * 2020/2/28
 * defination：
 */
@Mapper
@Repository
public interface FileUserMapper extends BaseMapper<FileUser>{
}
