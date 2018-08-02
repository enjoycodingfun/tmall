package com.qf.manager.service.impl;

import com.qf.manager.dao.TbGetMapper;
import com.qf.manager.dao.TbOrderMapper;
import com.qf.manager.dao.TbOrderitemsMapper;
import com.qf.manager.dao.TbUserMapper;
import com.qf.manager.pojo.po.*;
import com.qf.manager.pojo.vo.TbOrderCustom;
import com.qf.manager.service.OrderService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private TbOrderMapper tbOrderMapper;

    @Autowired
    private TbOrderitemsMapper tbOrderitemsMapper;

    @Autowired
    private TbUserMapper tbUserMapper;
    
    @Autowired
    private TbGetMapper tbGetMapper;

    @Override
    public List<TbOrderCustom> listOrder() {
        List<TbOrderCustom> orderCustoms = orderCustoms = new ArrayList<>();
        try {
            List<TbOrder> tbOrders = tbOrderMapper.selectByExample(null);

            for (TbOrder tbOrder:tbOrders
                 ) {
                TbOrderCustom orderCustom = new TbOrderCustom();
                BeanUtils.copyProperties(orderCustom,tbOrder);
                orderCustom.setUname(tbUserMapper.selectByPrimaryKey(tbOrder.getUid()).getUname());
                TbGet tbGet = tbGetMapper.selectByPrimaryKey(tbOrder.getGid());
                orderCustom.setAddress(tbGet.getAddress());
                orderCustom.setGname(tbGet.getGnama());
                orderCustom.setTell(tbGet.getTell());

                orderCustoms.add(orderCustom);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return orderCustoms;


    }

    @Override
    public List<TbOrderitems> listOrderItem(String oid) {


        if(oid!=null&&oid!=""&&"undefined".equals(oid)){
            TbOrderitemsExample tbOrderitemsExample = new TbOrderitemsExample();
            TbOrderitemsExample.Criteria criteria = tbOrderitemsExample.createCriteria();
            criteria.andOidEqualTo(oid);
            return tbOrderitemsMapper.selectByExample(tbOrderitemsExample);
        }else{
            return tbOrderitemsMapper.selectByExample(null);
        }

    }

    @Override
    public int removeOrder(String oid) {

        return tbOrderMapper.deleteByPrimaryKey(oid);
    }

    /*@Override
    public List<TbOrder> listOrderByCondition(String oid, Integer status, Date firsttime, Date lasttime) {
        return null;
    }*/

    @Override
    public int getOrderConut() {
        return tbOrderMapper.countByExample(null);
    }

    @Override
    public int removeListOrder(String ids) {
        String[] oids = ids.split(",");
        int result = 1;
        for (String oid :oids){
            if (result==1){
                result = tbOrderMapper.deleteByPrimaryKey(oid);
            }
        }
        return result;
    }

    @Override
    public TbOrderCustom getOrderCustom(String oid) {
        TbOrderCustom orderCustom = new TbOrderCustom();
        try {
            TbOrder tbOrder = tbOrderMapper.selectByPrimaryKey(oid);
            BeanUtils.copyProperties(orderCustom,tbOrder);

            orderCustom.setUname(tbUserMapper.selectByPrimaryKey(tbOrder.getUid()).getUname());
            TbGet tbGet = tbGetMapper.selectByPrimaryKey(tbOrder.getGid());
            orderCustom.setAddress(tbGet.getAddress());
            orderCustom.setGname(tbGet.getGnama());
            orderCustom.setTell(tbGet.getTell());

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return orderCustom;
    }

    @Override
    public int updataOrder(TbOrderCustom tbOrderCustom) {
        int result = 1;

        TbGet tbGet = new TbGet();
        tbGet.setGid(tbOrderCustom.getGid());
        tbGet.setAddress(tbOrderCustom.getAddress());
        tbGet.setGnama(tbOrderCustom.getGname());
        tbGet.setTell(tbOrderCustom.getTell());
        System.out.println(tbGet);

        result = tbGetMapper.updateByPrimaryKeySelective(tbGet);

        return result;
    }

    @Override
    public int getOrderItemConut() {
        return tbOrderitemsMapper.countByExample(null);
    }

    @Override
    public int removeOrderItem(String otid) {
        return tbOrderitemsMapper.deleteByPrimaryKey(otid);
    }

    @Override
    public int removeListOrderItem(String ids) {
        String[] otids = ids.split(",");
        int result = 1;
        for (String otid :otids){
            if (result==1){
                result = tbOrderitemsMapper.deleteByPrimaryKey(otid);
            }
        }
        return result;
    }
}
