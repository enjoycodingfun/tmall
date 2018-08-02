package com.qf.manager.service.impl;

import com.qf.manager.pojo.dto.DataTablesResult;
import com.qf.manager.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class productServiceImpl implements ProductService {
    @Override
    public DataTablesResult getItemList(int draw, int start, int length, int cid, String search, String orderColumn, String orderDir) {
        return null;
    }



   /* @Override
    public DataTablesResult getItemList(int draw, int start, int length, int cid, String search,
                                        String orderCol, String orderDir) {

        DataTablesResult result=new DataTablesResult();

        //分页执行查询返回结果
        PageHelper.startPage(start/length+1,length);
        List<TbItem> list = tbItemMapper.selectItemByCondition(cid,"%"+search+"%",orderCol,orderDir);
        PageInfo<TbItem> pageInfo=new PageInfo<>(list);
        result.setRecordsFiltered((int)pageInfo.getTotal());
        result.setRecordsTotal(getAllItemCount().getRecordsTotal());

        result.setDraw(draw);
        result.setData(list);

        return result;
    }*/
}
