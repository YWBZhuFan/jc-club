package com.jichi.subject.common.entity;

import lombok.Data;

@Data
public class PageInfo {

    private Integer pageNo = 1;
    private Integer pageSize = 10;


    public Integer getPageNo() {
        if (pageNo < 1) return 1;
        return pageNo;
    }

    public Integer getPageSize() {
        if (pageSize < 1) return 20;
        return pageSize;
    }
}
