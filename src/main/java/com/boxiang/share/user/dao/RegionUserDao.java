package com.boxiang.share.user.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.user.po.RegionUser;

public interface RegionUserDao extends IMybatisBaseDao<RegionUser> {
    
    void batchUpdate(List<RegionUser> tRegionUserList);
}