package com.qf.manager.web;

import com.qf.common.utils.FastDFSUtils;
import com.qf.common.utils.JsonUtils;
import com.qf.common.utils.PropKit;
import com.qf.common.utils.StrKit;
import com.qf.manager.pojo.dto.ItemResult;
import com.qf.manager.pojo.dto.PageParam;
import com.qf.common.utils.FastDFSFile;
import com.qf.manager.pojo.po.TbProducts;
import com.qf.manager.pojo.vo.Category;
import com.qf.manager.pojo.vo.ItemCat;
import com.qf.manager.pojo.vo.ItemSeach;
import com.qf.manager.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class ItemAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemService itemService;

    /**
     * 分页搜索显示所有商品
     * @param pageParam
     * @param itemSeach
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/items",method = RequestMethod.GET)
    public ItemResult<ItemCat> listItems(PageParam pageParam,ItemSeach itemSeach){

        ItemResult<ItemCat> data = null;
        try {
            data = itemService.listItems(pageParam,itemSeach);
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }
    @ResponseBody
    @RequestMapping(value = "/item/list",method = RequestMethod.GET)
    public Map<String,List<ItemCat>> listItems(){

        Map<String,List<ItemCat>> map = new HashMap<>();
        try {
            List<ItemCat> data = itemService.listProduct();
            map.put("data",data);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/listSearch",method = RequestMethod.GET)
    public Map<String,List<ItemCat>> listSearch(String searchKey){

        Map<String,List<ItemCat>> map = new HashMap<>();
        try {
            List<ItemCat>  data = itemService.listSearch(searchKey);
            map.put("data",data);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value =("/item/remove"),method = RequestMethod.POST)
    public int removeItems(@RequestParam("ids[]")List<String> ids){

        int result = itemService.removeItemMary(ids);
        return result;
    }

    /**
     * 单个删除
     * @param pid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/productDelete",method = RequestMethod.POST)
    public int deleteProduct(String pid){

        int result = itemService.updateProduct(pid);

        return result;
    }

    /**
     * 查询类别
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/item/catList",method = RequestMethod.GET)
    public List<Category> listCategory(HttpSession session){
        List<Category> result = itemService.listCategory();

        session.setAttribute("result",result);

        return result;
    }

    /**
     * 批量删除2
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value =("/removeProduct"),method = RequestMethod.GET)
    public int removeProduct(String ids){
        int data = itemService.removeProduct(ids);
        return data;
    }

    /**
     * 商品下架
     * @param pid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/itemStop",method = RequestMethod.GET)
    public int itemStop(String pid){
        int data = itemService.itemStop(pid);
        return data;
    }

    /**
     * 商品发布
     * @param pid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/itemStart",method = RequestMethod.GET)
    public int itemStart(String pid){
        int data = itemService.itemStart(pid);
        return data;
    }

    @ResponseBody
    @RequestMapping(value = "/item/cat/list",method = RequestMethod.GET)
    public List<Category> getItemCatList(@RequestParam(name="id",defaultValue = "0") int parentId){

        System.out.println(parentId+"PPPPP");
        List<Category> list=itemService.listCategory();
        return list;
    }

    /**
     * 根据Pid查找
     * @param pid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listProductByPid",method = RequestMethod.GET)
    public ItemCat listProductByPid(String pid){

        System.out.println(pid);
        ItemCat data = null;
        try {
            data = itemService.listProductByPid(pid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }

    @ResponseBody
    @RequestMapping(value = "/updateProduct",method = RequestMethod.GET)
    public String updateProduct(TbProducts p){
        System.out.println(p);
        System.out.println(p.getPname());
        int result = itemService.editProduct(p);
        return "result";
    }

    @ResponseBody
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public String uploadImage(@RequestParam MultipartFile file) {

        Map<String, Object> map = new HashMap<String, Object>();
        String jsonString = "";
        try {
            //1 将文件上传到fdfs
            //a 初始化实体类
            FastDFSFile fastDFSFile = new FastDFSFile(file.getBytes(), file.getOriginalFilename(), file.getSize());
            //b 上传
            //path  /group1/M00/00/00/xxxx.jpg
            //模拟上传   /usr/bin/fdfs_upload_file /etc/fdfs/client.conf  /opt/soft/2.jpg
            ///group1/M00/00/00/xxxx.jpg
            // http://101.132.38.253/group1/M00/00/00/xxxx.jpg
            String filePath = FastDFSUtils.uploadFile(fastDFSFile);
            //2 回显到富文本编辑器中
            String basePath = PropKit.use("fdfs_client.conf").get("fdfs_server");
            //判断filePath是否为空，不为空上传成功
            if(StrKit.notBlank(filePath)){
                //不为空
                map.put("code",0);
                map.put("msg", "success");
                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put("src", basePath + "/" + filePath);
                map.put("data", dataMap);
            }else{
                map.put("code", 1);
                map.put("msg", "fail");
                Map<String,Object> dataMap = new HashMap<String,Object>();
                dataMap.put("src", "");
                map.put("data", dataMap);
            }
            jsonString = JsonUtils.objectToJson(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return jsonString;
    }

}
