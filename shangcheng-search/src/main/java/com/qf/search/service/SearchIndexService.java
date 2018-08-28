package com.qf.search.service;

import com.qf.search.pojo.dto.TbProductsIndexResult;


public interface SearchIndexService {

    TbProductsIndexResult searchIndex(String keyword, int pageIndex, int pageSize);
}
