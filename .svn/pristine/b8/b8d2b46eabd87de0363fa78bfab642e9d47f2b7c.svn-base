package com.boxiang.share.product.car.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.car.dao.CarMainDao;
import com.boxiang.share.product.car.po.CarMain;
import com.boxiang.share.product.car.service.CarMainService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("carMain")
public class CarMainServiceImpl implements CarMainService {
	@Resource(name="carMainDao")
	private CarMainDao carMainDao;
	
	@Override
	public List<CarMain> selectList(CarMain carMain) {
		return carMainDao.selectList(carMain);
	}

	@Override
	public Page<CarMain> queryListPage(Page<CarMain> page) {
	    page.setResultList(carMainDao.queryListPage(page));
		return page;
	}
	
	@Override
	public CarMain queryById(Integer id) {
		return carMainDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(CarMain carMain) {
		carMainDao.insert(carMain);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(Integer id) {
		carMainDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(CarMain carMain) {
		carMainDao.update(carMain);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<CarMain> carMainList) {
		carMainDao.batchUpdate(carMainList);
	}
}