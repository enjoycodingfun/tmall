package com.qf.manager.service;

import com.qf.manager.pojo.po.TbAdmin;

import java.util.List;

public interface AdminService {
    int adminCount();

    List<TbAdmin> adminList();

    int delAdmin(String aid);

    int delMoreAdmin(String ids);

    int checkUsername(String username);

    int checkTell(String tell);

    int updateAdmin(TbAdmin tbAdmin);

    int addAdmin(TbAdmin tbAdmin);

    TbAdmin checkById(String aid);

}
