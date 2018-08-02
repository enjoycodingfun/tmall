package com.qf.manager.web;

import com.qf.manager.pojo.po.TbUser;

import com.qf.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ResponseBody
@Controller
public class UserAction {

    @Autowired
    private UserService userService;

    //展示所有
    @RequestMapping(value = "/user/userList", method = RequestMethod.GET)
    public Map<String, Object> userList() {
        Map<String, Object> map = new HashMap<>();
        List<TbUser> list = userService.findAll();
        map.put("data", list);
        return map;
    }

    //获取用户数量
    @RequestMapping(value = "/user/getUserCount", method = RequestMethod.GET)
    public Map<String, Object> getUserCount() {
        Map<String, Object> map = new HashMap<>();
        int count = userService.getUserCount();
        map.put("count", count);
        return map;
    }

    //删除
    @RequestMapping(value = "/user/removeUser",method = RequestMethod.GET)
    public Map<String,Object> delUser(String uid){
        System.out.println(uid);
        Map<String, Object> map = new HashMap<>();
        int i = userService.delUser(uid);
        map.put("result",i);
        System.out.println(i);
        return map;
    }

    //批量删除
    @RequestMapping(value = "/user/delMoreUser",method = RequestMethod.GET)
    public Map<String,Object> delMoreUser(String ids){

        Map<String, Object> map = new HashMap<>();
        int i = userService.delMoreUser(ids);
        if (i>0){
            map.put("flag",true);
        }else{
            map.put("flag",false);
        }
        return map;
    }

}
