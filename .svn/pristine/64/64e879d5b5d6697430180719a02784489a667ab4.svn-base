package com.boxiang.share.product.activity.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.activity.dao.ActivityDao;
import com.boxiang.share.product.activity.po.Activity;
import com.boxiang.share.product.activity.service.ActivityService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("activity")
public class ActivityServiceImpl implements ActivityService {
    @Resource(name = "activityDao")
    private ActivityDao activityDao;

    @Override
    public List<Activity> selectList(Activity activity) {
        return activityDao.selectList(activity);
    }

    @Override
    public Page<Activity> queryListPage(Page<Activity> page) {
        page.setResultList(activityDao.queryListPage(page));
        return page;
    }

    @Override
    public Activity queryById(Integer id) {
        return activityDao.queryById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void add(Activity activity) {
        activityDao.insert(activity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void delete(Integer id) {
        activityDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void update(Activity activity) {
        activityDao.update(activity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void batchUpdate(List<Activity> activityList) {
        activityDao.batchUpdate(activityList);
    }

    @Override
    public List<Activity> queryListPageV2(Object page) {
        return activityDao.queryListPageV2(page);
    }
}