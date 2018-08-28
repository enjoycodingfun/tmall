package com.qf.myservice.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * User: DHC
 * Date: 2018/8/7
 * Time: 14:18
 * Version:V1.0
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext cxt = new ClassPathXmlApplicationContext("spring-provider.xml");
        cxt.start();
        System.in.read();
    }
}
