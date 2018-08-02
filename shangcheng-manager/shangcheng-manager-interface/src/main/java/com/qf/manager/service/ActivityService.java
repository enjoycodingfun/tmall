package com.qf.manager.service;

import com.qf.manager.pojo.po.TbActivity;
import com.qf.manager.pojo.vo.ActivityVo;

import java.util.List;

public interface ActivityService {

    List<TbActivity> listActives();

    TbActivity listActiveById(String aid);

    int updataActive(ActivityVo av);

    int removeActivity(String aid);

    int saveActivity(ActivityVo activityVo);

    int activityCount();

    int removeListActivity(List<String> ids);
}
