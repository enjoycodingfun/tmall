package com.qf.manager.service.impl;

import com.qf.common.utils.IDUtil;
import com.qf.manager.dao.TbActivityMapper;
import com.qf.manager.dao.TbProductsActivityMapper;
import com.qf.manager.pojo.po.TbActivity;
import com.qf.manager.pojo.po.TbActivityExample;
import com.qf.manager.pojo.po.TbProductsActivity;
import com.qf.manager.pojo.po.TbProductsActivityExample;
import com.qf.manager.pojo.vo.ActivityVo;
import com.qf.manager.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private TbActivityMapper tbActivityDao;
    @Autowired
    private TbProductsActivityMapper tbProdcutsActivity;

    @Override
    public List<TbActivity> listActives() {
        List<TbActivity> tbActivities = tbActivityDao.selectByExample(null);
        System.out.print(tbActivities);
        return tbActivities;
    }

    @Override
    public TbActivity listActiveById(String aid) {

        TbActivity tbActivity = tbActivityDao.selectByPrimaryKey(aid);

        return tbActivity;
    }

    @Override
    public int updataActive(ActivityVo av) {
        TbActivityExample example = new TbActivityExample();
        TbActivityExample.Criteria criteria = example.createCriteria();
        criteria.andAidEqualTo(av.getAid());
        int i = tbActivityDao.updateByExampleSelective(av,example);
        return i;
    }

    @Override
    public int removeActivity(String aid) {

        int i = tbActivityDao.deleteByPrimaryKey(aid);
        if (i==1){
           TbProductsActivityExample tbProdcutsActivityExample = new TbProductsActivityExample();
            TbProductsActivityExample.Criteria criteria = tbProdcutsActivityExample.createCriteria();
            criteria.andAidEqualTo(aid);
            int i1 = tbProdcutsActivity.deleteByExample(tbProdcutsActivityExample);
            return  i1;
        }
        return i;
    }

    @Override
    public int saveActivity(ActivityVo activityVo) {
        activityVo.setAid(IDUtil.getGuid());
        int i = tbActivityDao.insertSelective(activityVo);
        return i;
    }

    @Override
    public int activityCount() {

        int i = tbActivityDao.countByExample(null);
        return i;
    }

    @Override
    public int removeListActivity(List<String> ids) {
       TbActivityExample example = new TbActivityExample();
        TbActivityExample.Criteria criteria = example.createCriteria();
        criteria.andAidIn(ids);
        int i = tbActivityDao.deleteByExample(example);
        if (i==1){
            TbProductsActivityExample example1 = new TbProductsActivityExample();
            TbProductsActivityExample.Criteria criteria1 = example1.createCriteria();
            criteria.andAidIn(ids);
            List<TbProductsActivity> tbProductsActivities = tbProdcutsActivity.selectByExample(example1);
            if(tbProductsActivities!=null){
                tbProdcutsActivity.deleteByExample(example1);
            }
            return i;
        }
        return i;
    }
}
