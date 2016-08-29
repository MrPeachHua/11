package com.boxiang.share.product.activity.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.boxiang.share.utils.ResultMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.activity.dao.CarLifeActivityDao;
import com.boxiang.share.product.activity.po.CarLifeActivity;
import com.boxiang.share.product.activity.service.CarLifeActivityService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("carLifeActivity")
public class CarLifeActivityServiceImpl implements CarLifeActivityService {
    @Resource(name = "carLifeActivityDao")
    private CarLifeActivityDao carLifeActivityDao;

    @Override
    public List<CarLifeActivity> selectList(CarLifeActivity carLifeActivity) {
        return carLifeActivityDao.selectList(carLifeActivity);
    }

    @Override
    public Page<CarLifeActivity> queryListPage(Page<CarLifeActivity> page) {
        page.setResultList(carLifeActivityDao.queryListPage(page));
        return page;
    }

    @Override
    public CarLifeActivity queryById(Integer id) {
        return carLifeActivityDao.queryById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void add(CarLifeActivity carLifeActivity) {
        carLifeActivityDao.insert(carLifeActivity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void delete(Integer id) {
        carLifeActivityDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void update(CarLifeActivity carLifeActivity) {
        carLifeActivityDao.update(carLifeActivity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void batchUpdate(List<CarLifeActivity> carLifeActivityList) {
        carLifeActivityDao.batchUpdate(carLifeActivityList);
    }

    @Override
    public List<CarLifeActivity> queryListPageV2(Page<CarLifeActivity> page) {
        return carLifeActivityDao.queryListPageV2(page);
    }

    @Override
    public List<Map<String, Object>> paramsFilter(List<CarLifeActivity> list, HttpServletRequest request) throws IllegalAccessException {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        Date now = new Date();
        List<Map<String, Object>> mapList = new ArrayList<>(list.size());
        for (CarLifeActivity item : list) {
            item.setImagePath(basePath + item.getImagePath());
            Map<String, Object> map = new ResultMap<>(item);
            map.remove("html");
            if (item.getType().equals("1")) {
                long s = (item.getEndDate().getTime() - now.getTime()) / 1000;
                long d = s / (60 * 60 * 24);
                long h = s / (60 * 60) - (d * 24);
                long m = s / 60 - (h * 60) - (d * 24 * 60);
                StringBuilder remainTime = new StringBuilder();
                if (d > 0) {
                    remainTime.append(String.format("%02d天", d));
                }
                if (d > 0 || h > 0) {
                    remainTime.append(String.format("%02d小时", h));
                }
                remainTime.append(String.format("%02d分钟", m));
                map.put("remainTime", remainTime.toString());
            }
            mapList.add(map);
        }
        return mapList;
    }

    @Override
    public List<Object> queryListPageV3(Object page) {
        return carLifeActivityDao.queryListPageV3(page);
    }

}