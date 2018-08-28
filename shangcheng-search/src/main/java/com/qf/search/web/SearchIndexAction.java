package com.qf.search.web;

import com.qf.common.utils.PropKit;
import com.qf.common.utils.StrKit;
import com.qf.search.pojo.dto.TbProductsIndexResult;
import com.qf.search.service.SearchIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Controller
public class SearchIndexAction {

    @Autowired
    private SearchIndexService searchIndexService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @ResponseBody
    public Map search(String keyword, @RequestParam(defaultValue = "1") int pageIndex, Model model, HttpServletRequest request, HttpServletResponse response){
        //pageIndex当前页的页码
        //pageSize每页显示的条数
        Map<String,Object> map = new HashMap<>();
        if(StrKit.notBlank(keyword)) {
            int pageSize = PropKit.use("file.properties").getInt("search.pagesize");
            //服务层查询
            TbProductsIndexResult result = searchIndexService.searchIndex(keyword, pageIndex, pageSize);
            //存放作用域中
            //model.addAttribute("list", result.getList());
            map.put("list",result.getList());
        }
        //转发到下一个页面
//        try {
//            request.getRequestDispatcher("localhost:84/shangcheng/html/product-list.html").forward(request,response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return map;
    }
}
