package com.boxiang.share.system.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.system.po.SysUsersRoles;

public interface SysUsersRolesDao extends IMybatisBaseDao<SysUsersRoles> {
    
    void batchUpdate(List<SysUsersRoles> sysUsersRolesList);
    void del(String id);
    void del2(String userId);
}