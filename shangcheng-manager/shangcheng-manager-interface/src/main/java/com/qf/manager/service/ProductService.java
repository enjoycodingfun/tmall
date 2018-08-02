package com.qf.manager.service;

import com.qf.manager.pojo.dto.DataTablesResult;

public interface ProductService {

    DataTablesResult getItemList(int draw, int start, int length, int cid, String search, String orderColumn, String orderDir);
}
