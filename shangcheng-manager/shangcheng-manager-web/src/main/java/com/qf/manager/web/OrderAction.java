package com.qf.manager.web;

import com.qf.manager.pojo.po.TbOrder;
import com.qf.manager.pojo.po.TbOrderitems;
import com.qf.manager.pojo.vo.TbOrderCustom;
import com.qf.manager.service.OrderService;
import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("/order")
public class OrderAction {

    @Autowired
    private OrderService orderService;

    //查询所有订单
    @RequestMapping(value = "/listOrder",method = RequestMethod.GET)
    public Map<String,Object> listOrder(){
        Map<String,Object> map = new HashMap<>();

        List<TbOrderCustom> orderCustoms = orderService.listOrder();

        map.put("data",orderCustoms);
        return map;
    }

    //查询所有订单项
    @RequestMapping(value = "/listOrderItem",method = RequestMethod.GET)
    public Map<String,Object> listOrderItem(String oid){
        System.out.println(oid);
        System.out.println(111);
        Map<String,Object> map = new HashMap<>();
        List<TbOrderitems> orderItems = orderService.listOrderItem(oid);
        map.put("data",orderItems);
        return map;

    }

    //删除订单
    @RequestMapping(value = "/removeOrder",method = RequestMethod.GET)
    public Map<String,Object> removeOrder(String oid){
        Map<String,Object> map = new HashMap<>();
        int result = orderService.removeOrder(oid);
        map.put("result",result);
        return map;

    }

    //删除订单
    @RequestMapping(value = "/removeOrderItem",method = RequestMethod.GET)
    public Map<String,Object> removeOrderItem(String otid){
        Map<String,Object> map = new HashMap<>();
        int result = orderService.removeOrderItem(otid);
        map.put("result",result);
        return map;

    }

    //批量删除订单
    @RequestMapping(value = "/removeListOrder",method = RequestMethod.GET)
    public Map<String,Object> removeListOrder(String ids){
        Map<String,Object> map = new HashMap<>();
        int result = orderService.removeListOrder(ids);
        map.put("result",result);
        return map;

    }

    //批量删除订单项
    @RequestMapping(value = "/removeListOrderItem",method = RequestMethod.GET)
    public Map<String,Object> removeListOrderItem(String ids){

        Map<String,Object> map = new HashMap<>();
        int result = orderService.removeListOrderItem(ids);
        map.put("result",result);
        return map;

    }
    /*@RequestMapping(value = "/listOrderByCondition",method = RequestMethod.GET)
    public Map<String,Object> listOrderByCondition(String oid,Integer status,Date firsttime,Date lasttime){

        Map<String,Object> map = new HashMap<>();
        List<TbOrder> tbOrders = orderService.listOrderByCondition(oid,status,firsttime,lasttime);

        map.put("tbOrders",tbOrders);
        return map;
    }*/


    //获得所有订单的数量
    @RequestMapping(value = "/getOrderConut",method = RequestMethod.GET)
    public Map<String,Object> getOrderConut(){

        Map<String,Object> map = new HashMap<>();
        int count = orderService.getOrderConut();

        map.put("count",count);
        return map;

    }

    //查询某个订单
    @RequestMapping(value = "/getOrderCustom",method = RequestMethod.GET)
    public Map<String,Object> getOrderCustom(String oid){

        Map<String,Object> map = new HashMap<>();
        TbOrderCustom orderCustom = orderService.getOrderCustom(oid);
        map.put("orderCustom",orderCustom);
        return map;

    }

    //修改订单
    @RequestMapping(value = "/updataOrder",method = RequestMethod.GET)
    public Map<String,Object> updataOrder(TbOrderCustom tbOrderCustom){
        Map<String,Object> map = new HashMap<>();
        int result= orderService.updataOrder(tbOrderCustom);
        map.put("result",result);
        return map;

    }

    //获得所有订单项的数量
    @RequestMapping(value = "/getOrderItemConut",method = RequestMethod.GET)
    public Map<String,Object> getOrderItemConut(){

        Map<String,Object> map = new HashMap<>();
        int count = orderService.getOrderItemConut();

        map.put("count",count);
        return map;

    }

}
