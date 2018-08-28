package com.qf.detail.dao;


import com.qf.detail.pojo.po.TbProducts;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {

    TbProducts findProductByPid(@Param("pid") String pid);
}

