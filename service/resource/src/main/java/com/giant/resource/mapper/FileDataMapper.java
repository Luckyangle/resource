package com.giant.resource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.giant.resource.entity.FileData;
import com.giant.resource.query.FileDataQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by Lvzhihang
 * 2020/2/27
 * defination：
 */
@Mapper
@Repository
public interface FileDataMapper extends BaseMapper<FileData>{

    List<FileData> search(FileDataQuery query);
}
