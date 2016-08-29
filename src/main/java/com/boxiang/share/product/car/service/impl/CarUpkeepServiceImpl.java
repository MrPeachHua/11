package com.boxiang.share.product.car.service.impl;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.car.dao.CarUpkeepDao;
import com.boxiang.share.product.car.po.CarUpkeep;
import com.boxiang.share.product.car.service.CarUpkeepService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@DataSource(DataSourceEnum.MASTER)
@Service("carUpkeep")
public class CarUpkeepServiceImpl implements CarUpkeepService {
    @Resource(name = "carUpkeepDao")
    private CarUpkeepDao carUpkeepDao;

    @Override
    public List<CarUpkeep> selectList(CarUpkeep carUpkeep) {
        return carUpkeepDao.selectList(carUpkeep);
    }

    @Override
    public Page<CarUpkeep> queryListPage(Page<CarUpkeep> page) {
        page.setResultList(carUpkeepDao.queryListPage(page));
        return page;
    }

    @Override
    public CarUpkeep queryById(String id) {
        return carUpkeepDao.queryById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void add(CarUpkeep carUpkeep) {
        carUpkeepDao.insert(carUpkeep);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void delete(String id) {
        carUpkeepDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void update(CarUpkeep carUpkeep) {
        carUpkeepDao.update(carUpkeep);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void batchUpdate(List<CarUpkeep> carUpkeepList) {
        carUpkeepDao.batchUpdate(carUpkeepList);
    }

    @Override
    public List<Map<String, Object>> gainupkeep(Map<String, Object> params) {
        return carUpkeepDao.gainupkeep(params);
    }
}