package com.boxiang.share.system.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.system.po.SysUsers;

public interface SysUsersDao extends IMybatisBaseDao<SysUsers> {
    
    void batchUpdate(List<SysUsers> sysUsersList);
    
    List<SysUsers> queryUserRoleByAccount(String userAccount);
    
    List<SysUsers> queryUserByRole(String roleId);
}