package com.qf.manager.service.impl;

import com.qf.manager.dao.TbAdminMapper;
import com.qf.manager.pojo.po.TbAdmin;
import com.qf.manager.pojo.po.TbAdminExample;
import com.qf.manager.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private TbAdminMapper tbAdminMapper;

    @Override
    public int adminCount() {

        return tbAdminMapper.countByExample(null);
    }

    @Override
    public List<TbAdmin> adminList() {

        return tbAdminMapper.selectByExample(null);
    }

    @Override
    public int delAdmin(String aid) {

        return tbAdminMapper.deleteByPrimaryKey(aid);
    }

    @Override
    public int delMoreAdmin(String ids) {

        String[] s = ids.split(",");
        List<String> list = new ArrayList<>();
        for (String str:s) {
            list.add(str);
        }
        TbAdminExample example = new TbAdminExample();
        TbAdminExample.Criteria criteria = example.createCriteria();
        criteria.andAidIn(list);
        return tbAdminMapper.deleteByExample(example);
    }

    @Override
    public int checkUsername(String username) {

        TbAdminExample example = new TbAdminExample();
        TbAdminExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        return tbAdminMapper.countByExample(example);
    }

    @Override
    public int checkTell(String tell) {
        TbAdminExample example = new TbAdminExample();
        TbAdminExample.Criteria criteria = example.createCriteria();
        criteria.andTellEqualTo(tell);
        return tbAdminMapper.countByExample(example);
    }

    @Override
    public int updateAdmin(TbAdmin tbAdmin) {

        return tbAdminMapper.updateByPrimaryKeySelective(tbAdmin);
    }

    @Override
    public int addAdmin(TbAdmin tbAdmin) {

        return tbAdminMapper.insert(tbAdmin);
    }

    @Override
    public TbAdmin  checkById(String aid) {

        if (aid==null||aid.equals("")){
            TbAdmin tbAdmin = new TbAdmin();
            tbAdmin.setUsername("");
            tbAdmin.setTell("");
            return tbAdmin;
        }
        return tbAdminMapper.selectByPrimaryKey(aid);
    }
}
