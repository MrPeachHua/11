package com.boxiang.share.product.car.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.car.dao.CarUpkeepDictDao;
import com.boxiang.share.product.car.po.CarUpkeepDict;
import com.boxiang.share.product.car.service.CarUpkeepDictService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("carUpkeepDict")
public class CarUpkeepDictServiceImpl implements CarUpkeepDictService {
    @Resource(name = "carUpkeepDictDao")
    private CarUpkeepDictDao carUpkeepDictDao;

    @Override
    public List<CarUpkeepDict> selectList(CarUpkeepDict carUpkeepDict) {
        return carUpkeepDictDao.selectList(carUpkeepDict);
    }

    @Override
    public Page<CarUpkeepDict> queryListPage(Page<CarUpkeepDict> page) {
        page.setResultList(carUpkeepDictDao.queryListPage(page));
        return page;
    }

    @Override
    public CarUpkeepDict queryById(String id) {
        return carUpkeepDictDao.queryById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void add(CarUpkeepDict carUpkeepDict) {
        carUpkeepDictDao.insert(carUpkeepDict);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void delete(String id) {
        carUpkeepDictDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void update(CarUpkeepDict carUpkeepDict) {
        carUpkeepDictDao.update(carUpkeepDict);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void batchUpdate(List<CarUpkeepDict> carUpkeepDictList) {
        carUpkeepDictDao.batchUpdate(carUpkeepDictList);
    }
}