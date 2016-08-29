package com.boxiang.share.product.activity.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.activity.po.Activity;

public interface ActivityDao extends IMybatisBaseDao<Activity> {
    
    void batchUpdate(List<Activity> activityList);

    List<Activity> queryListPageV2(Object page);
}