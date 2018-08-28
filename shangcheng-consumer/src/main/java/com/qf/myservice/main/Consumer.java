package com.qf.myservice.main;

import com.qf.myservice.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: DHC
 * Date: 2018/8/7
 * Time: 14:41
 * Version:V1.0
 */
public class Consumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext cxt = new ClassPathXmlApplicationContext("spring-consumer.xml");
        cxt.start();
        DemoService demoService=(DemoService)cxt.getBean("demoService");
        System.out.println(demoService.sayHello("zhangze"));
    }
}
