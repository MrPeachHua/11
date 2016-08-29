package com.boxiang.share.system.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.system.po.AppVersion;

public interface AppVersionDao extends IMybatisBaseDao<AppVersion> {
    
    void batchUpdate(List<AppVersion> tAppVersionList);
}