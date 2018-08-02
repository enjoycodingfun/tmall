package com.qf.manager.dao;

import com.qf.manager.pojo.po.TbActivity;
import com.qf.manager.pojo.po.TbActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbActivityMapper {
    int countByExample(TbActivityExample example);

    int deleteByExample(TbActivityExample example);

    int deleteByPrimaryKey(String aid);

    int insert(TbActivity record);

    int insertSelective(TbActivity record);

    List<TbActivity> selectByExample(TbActivityExample example);

    TbActivity selectByPrimaryKey(String aid);

    int updateByExampleSelective(@Param("record") TbActivity record, @Param("example") TbActivityExample example);

    int updateByExample(@Param("record") TbActivity record, @Param("example") TbActivityExample example);

    int updateByPrimaryKeySelective(TbActivity record);

    int updateByPrimaryKey(TbActivity record);
}