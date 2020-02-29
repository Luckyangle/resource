package com.giant.resource.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Created by Lvzhihang
 * 2020/2/28
 * defination：
 */
@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class FileDataQuery {
    private String fileName;
    private String fileGroup;
    private String userId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
