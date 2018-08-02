package com.qf.manager.service;

import com.qf.manager.pojo.dto.ItemResult;
import com.qf.manager.pojo.dto.PageParam;
import com.qf.manager.pojo.po.TbItem;
import com.qf.manager.pojo.po.TbProducts;
import com.qf.manager.pojo.vo.Category;
import com.qf.manager.pojo.vo.ItemCat;
import com.qf.manager.pojo.vo.ItemSeach;

import java.util.List;

public interface ItemService {

    ItemResult<ItemCat> listItems(PageParam pageParam, ItemSeach itemSeach);

    int removeItemMary(List<String> ids);

    int updateProduct(String pid);

    List<Category> listCategory();

    List<ItemCat> listProduct();

    int removeProduct(String ids);

    List<ItemCat> listSearch(String searchKey);

    int itemStop(String pid);

    int itemStart(String pid);

    ItemCat listProductByPid(String pid);

    int editProduct(TbProducts p);

   /* List<ZTreeNode> getItemCatList(int parentId);*/
}
