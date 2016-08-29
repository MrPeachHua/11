package com.boxiang.share.system.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.system.po.SysAuthorities;
import com.boxiang.share.system.po.SysRolesAuthorities;

public interface SysRolesAuthoritiesDao extends IMybatisBaseDao<SysRolesAuthorities> {
    
    void batchUpdate(List<SysRolesAuthorities> sysRolesAuthoritiesList);
    int del(String roleId);
    int delByAuthId(String authId);
    List<SysAuthorities> selectList2(String userId);

}