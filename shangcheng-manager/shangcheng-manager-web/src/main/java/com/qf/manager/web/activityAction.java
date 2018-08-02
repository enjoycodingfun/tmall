package com.qf.manager.web;

import com.qf.manager.pojo.po.TbActivity;
import com.qf.manager.pojo.vo.ActivityVo;
import com.qf.manager.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class activityAction {

    @Autowired
    private ActivityService activityService;

    //查询所有活动
    @ResponseBody
    @RequestMapping("/activitys")
    public  Map<String,Object> listActives(){
        Map<String,Object> map = new HashMap<>();
        List<TbActivity> activities = null;
        int i = 0;
        try{
            activities = activityService.listActives();
            i = activityService.activityCount();
            map.put("data",activities);
            map.put("count",i);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.print(activities);
        return map;
    }
    //查询数量
    @ResponseBody
    @RequestMapping("/activityCount")
    public  Map<String,Object> activityCount(){
        Map<String,Object> map = new HashMap<>();

        int i = 0;
        try{
            i = activityService.activityCount();
            map.put("count",i);
        }catch (Exception e){
            e.printStackTrace();
        }

        return map;
    }


    //编辑回显
    @ResponseBody
    @RequestMapping(value = "/editActivity",method = RequestMethod.GET)
    public Map<String,Object> listActive(String aid){
        TbActivity activitie = null;
        Map<String,Object> map = new HashMap<>();
        try {
            activitie = activityService.listActiveById(aid);
            map.put("data",activitie);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    //修改活动
    @ResponseBody
    @RequestMapping(value = "/activity/updataActivity")
    public Map<String,Object> updataActivity(ActivityVo activityVo){
        int i = 0;
        Map<String,Object> map = new HashMap<>();
        try {
            i = activityService.updataActive(activityVo);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(i==1){
            map.put("success",true);
        }
        return map;
    }
    //删除活动
    @ResponseBody
    @RequestMapping(value = "/removeActivity")
    public  Map<String,Object> removeActivity(String aid){
        Map<String,Object> map = new HashMap<>();
        System.out.println(aid);
        int i = 0;
        try {
            i = activityService.removeActivity(aid);
            System.out.println(i);
            map.put("i",i);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    //批量删除活动
    @ResponseBody
    @RequestMapping(value = "/removeListActivity")
    public  Map<String,Object> removeListActivity(@RequestParam("ids[]") List<String> ids){
        Map<String,Object> map = new HashMap<>();


        try {
           int i = activityService.removeListActivity(ids);

            map.put("i",i);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    //添加活动
    @ResponseBody
    @RequestMapping(value = "/saveActivity",method = RequestMethod.GET)
    public int saveActivity(ActivityVo activityVo){
        int i = 0;
        try {
            i = activityService.saveActivity(activityVo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return i;
    }
}
