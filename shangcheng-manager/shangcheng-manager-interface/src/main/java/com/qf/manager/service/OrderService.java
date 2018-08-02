package com.qf.manager.service;

import com.qf.manager.pojo.po.TbOrderitems;
import com.qf.manager.pojo.vo.TbOrderCustom;

import java.util.List;

public interface OrderService {
    List<TbOrderCustom> listOrder();

    List<TbOrderitems> listOrderItem(String oid);

    int removeOrder(String oid);

    int getOrderConut();

    int removeListOrder(String ids);

    TbOrderCustom getOrderCustom(String oid);

    int updataOrder(TbOrderCustom tbOrderCustom);

    int getOrderItemConut();

    int removeOrderItem(String otid);

    int removeListOrderItem(String ids);
}
