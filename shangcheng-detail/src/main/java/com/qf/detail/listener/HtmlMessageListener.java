package com.qf.detail.listener;

import com.qf.detail.dao.ProductDao;
import com.qf.detail.pojo.po.TbProducts;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class HtmlMessageListener implements MessageListener {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Override
    public void onMessage(Message message) {


        try {
            //接收消息
            TextMessage textmessage =  (TextMessage)message;
            String pid = textmessage.getText();

            //查询商品
            TbProducts tbProducts = productDao.findProductByPid(pid);
            //生成静态页面获取配置对象
            Configuration configuration = freeMarkerConfigurer.getConfiguration();
            Template template = configuration.getTemplate("Productdetail.ftl");
            //构建数据模型
            Map<String,Object> data = new HashMap<>();
            data.put("product",tbProducts);
            //创建writer
            Writer out = new FileWriter("d:/ftl/"+pid+".html");
            template.process(data,out);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
