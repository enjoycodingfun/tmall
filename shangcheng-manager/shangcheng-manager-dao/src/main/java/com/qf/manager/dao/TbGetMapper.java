package com.qf.manager.dao;

import com.qf.manager.pojo.po.TbGet;
import com.qf.manager.pojo.po.TbGetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbGetMapper {
    int countByExample(TbGetExample example);

    int deleteByExample(TbGetExample example);

    int deleteByPrimaryKey(String gid);

    int insert(TbGet record);

    int insertSelective(TbGet record);

    List<TbGet> selectByExample(TbGetExample example);

    TbGet selectByPrimaryKey(String gid);

    int updateByExampleSelective(@Param("record") TbGet record, @Param("example") TbGetExample example);

    int updateByExample(@Param("record") TbGet record, @Param("example") TbGetExample example);

    int updateByPrimaryKeySelective(TbGet record);

    int updateByPrimaryKey(TbGet record);
}