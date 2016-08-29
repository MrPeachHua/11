package com.boxiang.share.system.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.system.po.SysAuthoritiesResources;

public interface SysAuthoritiesResourcesDao extends IMybatisBaseDao<SysAuthoritiesResources> {
    
    void batchUpdate(List<SysAuthoritiesResources> sysAuthoritiesResourcesList);
    int del(String id);
}