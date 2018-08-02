package com.qf.manager.service.impl;

import com.qf.manager.dao.TbUserMapper;
import com.qf.manager.pojo.po.TbUser;
import com.qf.manager.pojo.po.TbUserExample;
import com.qf.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TbUserMapper userMapper;

    @Override
    public List<TbUser> findAll() {

        return userMapper.selectByExample(null);
    }

    @Override
    public int delUser(String uid) {

        return userMapper.deleteByPrimaryKey(uid);
    }

    @Override
    public int delMoreUser(String ids) {

        String[] s = ids.split(",");
        List<String> list = new ArrayList<>();
        for (String str:s) {
            list.add(str);
        }
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUidIn(list);
        return userMapper.deleteByExample(example);
    }

    @Override
    public int getUserCount() {
        return userMapper.countByExample(null);
    }
}
