package com.qf.search.dao;

import com.qf.search.pojo.vo.TbProductsIndex;

public interface TbProductsIndexMapper {

    TbProductsIndex getProductsByPid(String pid);
}
