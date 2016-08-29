package com.boxiang.share.system.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.system.po.SysAuthorities;

public interface SysAuthoritiesDao extends IMybatisBaseDao<SysAuthorities> {
    
    void batchUpdate(List<SysAuthorities> sysAuthoritiesList);
    
    List<String> queryAllAuthName();
    
    List<String> loadUserAuthoritiesByAccount(String userAccount);
    List<SysAuthorities> queryAuthorities(String resourseId);
    List<SysAuthorities> queryAuthoritiesByRoles(String rolesId);
}