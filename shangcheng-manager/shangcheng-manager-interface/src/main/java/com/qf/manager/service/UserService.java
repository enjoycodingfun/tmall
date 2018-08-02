package com.qf.manager.service;

import com.qf.manager.pojo.po.TbUser;

import java.util.List;

public interface UserService {
    List<TbUser> findAll();

    int delUser(String uid);

    int delMoreUser(String ids);

    int getUserCount();
}
