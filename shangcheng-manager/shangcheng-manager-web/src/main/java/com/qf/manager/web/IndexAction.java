package com.qf.manager.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.net.ssl.SSLSession;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexAction {

    @RequestMapping(value = "/{page}",method = RequestMethod.GET)
    public String one(@PathVariable String page, String oid, HttpServletRequest request){
        if(oid!=null&&oid!=""){
            request.setAttribute("oid",oid);
        }
        return page;
    }

    @RequestMapping(value = "/pages/{pageName1}",method = RequestMethod.GET)
    public String two(@PathVariable String pageName1) {

        return "pages/"+pageName1;
    }

    @RequestMapping(value = "/pages/{pageName1}/{pageName2}",method = RequestMethod.GET)
    public String three(@PathVariable String pageName1,@PathVariable String pageName2){
        return "pages/"+pageName1+"/"+pageName2;
    }
}
