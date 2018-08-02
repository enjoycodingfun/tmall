package com.qf.manager.dao;

import com.qf.manager.pojo.po.TbProducts;
import com.qf.manager.pojo.po.TbProductsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbProductsMapper {
    int countByExample(TbProductsExample example);

    int deleteByExample(TbProductsExample example);

    int deleteByPrimaryKey(String pid);

    int insert(TbProducts record);

    int insertSelective(TbProducts record);

    List<TbProducts> selectByExample(TbProductsExample example);

    TbProducts selectByPrimaryKey(String pid);

    int updateByExampleSelective(@Param("record") TbProducts record, @Param("example") TbProductsExample example);

    int updateByExample(@Param("record") TbProducts record, @Param("example") TbProductsExample example);

    int updateByPrimaryKeySelective(TbProducts record);

    int updateByPrimaryKey(TbProducts record);
}