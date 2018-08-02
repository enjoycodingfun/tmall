package com.qf.manager.web;

import com.qf.common.utils.IDUtil;
import com.qf.manager.pojo.po.TbAdmin;
import com.qf.manager.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ResponseBody
@Controller
public class AdminAction {

    @Autowired
    private AdminService adminService;

    //获取管理员数量
    @RequestMapping(value = "/admin/adminCount",method = RequestMethod.GET)
    public Map<String,Object> adminCount(){

        Map<String,Object> map = new HashMap<>();
        int i = adminService.adminCount();
        map.put("result",i);
        map.put("success",true);
        map.put("message","出错了");
        return map;
    }

    //获取全部管理员信息
    @RequestMapping(value = "/admin/adminList",method = RequestMethod.GET)
    public Map<String,Object> adminList(){

        Map<String,Object> map = new HashMap<>();
        List<TbAdmin> list = adminService.adminList();
        map.put("data",list);
        return map;
    }

    //删除管理员(单)
    @RequestMapping(value = "/admin/delAdmin",method = RequestMethod.GET)
    public Map<String,Object> delAdmin(String aid){

        Map<String,Object> map = new HashMap<>();
        int i = adminService.delAdmin(aid);
        if(i>0){
            map.put("success",true);
        }else{
            map.put("success",false);
            map.put("message","删除失败");
        }
        return map;
    }

    //批量删除
    @RequestMapping(value = "/admin/delMoreAdmin",method = RequestMethod.GET)
    public Map<String,Object> delMoreAdmin(String ids){

        Map<String,Object> map = new HashMap<>();
        int i = adminService.delMoreAdmin(ids);
        if(i>0){
            map.put("success",true);
        }else{
            map.put("success",false);
            map.put("message","删除失败");
        }
        return map;
    }

    //验证用户名唯一
    @RequestMapping(value = "/admin/edit/username")
    public String checkUsername(String username,String aid){

        int i = adminService.checkUsername(username);
        TbAdmin tbAdmin = adminService.checkById(aid);
        if(i>0 && !username.equals(tbAdmin.getUsername())){
            return "false";
        }else {
            return "true";
        }
    }

    //验证手机号唯一
    @RequestMapping(value = "/admin/edit/tell")
    public String checkTell(String tell,String aid){

        int i = adminService.checkTell(tell);
        TbAdmin tbAdmin = adminService.checkById(aid);
        if(i>0 && !tell.equals(tbAdmin.getTell())){
            return "false";
        }else {
            return "true";
        }
    }

    //编辑管理员
    @RequestMapping(value = "/admin/updateAdmin")
    public Map<String,Object> updateAdmin(TbAdmin tbAdmin){

        Map<String,Object> map = new HashMap<>();
        int i = adminService.updateAdmin(tbAdmin);
        if(i>0){
            map.put("success",true);
        }else{
            map.put("success",false);
            map.put("message","修改失败");
        }
        return map;
    }

    //添加管理员
    @RequestMapping(value = "/admin/addAdmin")
    public Map<String,Object> addAdmin(TbAdmin tbAdmin){

        Map<String,Object> map = new HashMap<>();
        tbAdmin.setAid(IDUtil.getGuid());
        int i = adminService.addAdmin(tbAdmin);
        if(i>0){
            map.put("success",true);
        }else{
            map.put("success",false);
            map.put("message","添加失败");
        }
        return map;
    }

    //修改密码
    @RequestMapping(value = "/admin/changePass")
    public Map<String,Object> changePass(TbAdmin tbAdmin){

        int i =adminService.updateAdmin(tbAdmin);
        Map<String,Object> map = new HashMap<>();
        if(i>0){
            map.put("success",true);
        }else{
            map.put("success",false);
            map.put("message","未知错误");
        }
        return map;
    }

}
