package com.boxiang.share.system.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.system.po.SysResources;

public interface SysResourcesDao extends IMybatisBaseDao<SysResources> {
    
    void batchUpdate(List<SysResources> sysResourcesList);
    
    List<String> queryUrlByAuthName(String authName);
    List<SysResources> queryList(String resourceId);
}