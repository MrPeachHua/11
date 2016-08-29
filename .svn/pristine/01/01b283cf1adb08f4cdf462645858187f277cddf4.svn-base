package com.boxiang.share.product.carlife.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.carlife.dao.CarRentDao;
import com.boxiang.share.product.carlife.po.CarRent;
import com.boxiang.share.product.carlife.service.CarRentService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("carRent")
public class CarRentServiceImpl implements CarRentService {
	@Resource(name="carRentDao")
	private CarRentDao carRentDao;
	
	@Override
	public List<CarRent> selectList(CarRent carRent) {
		return carRentDao.selectList(carRent);
	}

	@Override
	public Page<CarRent> queryListPage(Page<CarRent> page) {
	    page.setResultList(carRentDao.queryListPage(page));
		return page;
	}
	
	@Override
	public CarRent queryById(Integer id) {
		return carRentDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(CarRent carRent) {
		carRentDao.insert(carRent);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(Integer id) {
		carRentDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(CarRent carRent) {
		carRentDao.update(carRent);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<CarRent> carRentList) {
		carRentDao.batchUpdate(carRentList);
	}
}