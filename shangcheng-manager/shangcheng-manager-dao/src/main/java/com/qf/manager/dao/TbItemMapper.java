package com.qf.manager.dao;

import com.qf.manager.pojo.dto.PageParam;
import com.qf.manager.pojo.po.TbItem;
import com.qf.manager.pojo.vo.Category;
import com.qf.manager.pojo.vo.ItemCat;
import com.qf.manager.pojo.vo.ItemSeach;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 封装商品数据访问层接口
 */
public interface TbItemMapper {
    /**
     * 计算商品表里的商品总数
     * @return
     */
    long countItems(@Param("itemSeach") ItemSeach itemSeach);

    /**
     * 分页查询商品数据
     * @param pageParam
     * @return
     */
    List<ItemCat> listItems(@Param("pageParam") PageParam pageParam, @Param("itemSeach") ItemSeach itemSeach);

    List<Category> listCategory();

    List<ItemCat> listProduct();

    List<ItemCat> listSearch(@Param("searchKey") String searchKey);

    ItemCat listProductByPid(@Param("pid") String pid);

    int editProduct(@Param("p") ItemCat p);
}
