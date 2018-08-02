package com.qf.manager.dao;

import com.qf.manager.pojo.po.TbProductsActivity;
import com.qf.manager.pojo.po.TbProductsActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbProductsActivityMapper {
    int countByExample(TbProductsActivityExample example);

    int deleteByExample(TbProductsActivityExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbProductsActivity record);

    int insertSelective(TbProductsActivity record);

    List<TbProductsActivity> selectByExample(TbProductsActivityExample example);

    TbProductsActivity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbProductsActivity record, @Param("example") TbProductsActivityExample example);

    int updateByExample(@Param("record") TbProductsActivity record, @Param("example") TbProductsActivityExample example);

    int updateByPrimaryKeySelective(TbProductsActivity record);

    int updateByPrimaryKey(TbProductsActivity record);
}