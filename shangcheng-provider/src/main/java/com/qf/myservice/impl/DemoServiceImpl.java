package com.qf.myservice.impl;

import com.qf.myservice.DemoService;

import java.util.HashMap;

public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "hello" + name;
    }
}
