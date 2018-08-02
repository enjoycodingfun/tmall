package com.qf.manager.dao;

import com.qf.manager.pojo.po.TbEvaluate;
import com.qf.manager.pojo.po.TbEvaluateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbEvaluateMapper {
    int countByExample(TbEvaluateExample example);

    int deleteByExample(TbEvaluateExample example);

    int deleteByPrimaryKey(String eid);

    int insert(TbEvaluate record);

    int insertSelective(TbEvaluate record);

    List<TbEvaluate> selectByExample(TbEvaluateExample example);

    TbEvaluate selectByPrimaryKey(String eid);

    int updateByExampleSelective(@Param("record") TbEvaluate record, @Param("example") TbEvaluateExample example);

    int updateByExample(@Param("record") TbEvaluate record, @Param("example") TbEvaluateExample example);

    int updateByPrimaryKeySelective(TbEvaluate record);

    int updateByPrimaryKey(TbEvaluate record);
}