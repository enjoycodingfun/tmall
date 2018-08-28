package com.qf.search.pojo.dto;

import com.qf.search.pojo.vo.TbProductsIndex;

import java.util.List;


public class TbProductsIndexResult {
    //符合条件的总记录数
    private long recordCount;
    //总页数
    private long totalPages;
    //指定分页的集合
    private List<TbProductsIndex> list;

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public List<TbProductsIndex> getList() {
        return list;
    }

    public void setList(List<TbProductsIndex> list) {
        this.list = list;
    }
}
