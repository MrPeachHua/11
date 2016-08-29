package com.boxiang.share.product.car.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.boxiang.share.product.car.dao.CarTypesDao;
import com.boxiang.share.product.car.po.CarTypes;
import com.boxiang.share.product.car.service.CarTypesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("carTypesService")
public class CarTypesServiceImpl implements CarTypesService {
	@Resource(name="carTypesDao")
	private CarTypesDao carTypesDao;
	
	@Override
	public List<CarTypes> selectList(CarTypes tCarTypes) {
		return carTypesDao.selectList(tCarTypes);
	}

	@Override
	public Page<CarTypes> queryListPage(Page<CarTypes> page) {
	    page.setResultList(carTypesDao.queryListPage(page));
		return page;
	}
	
	@Override
	public CarTypes queryById(Integer id) {
		return carTypesDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(CarTypes tCarTypes) {
		carTypesDao.insert(tCarTypes);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(Integer id) {
		carTypesDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(CarTypes tCarTypes) {
		carTypesDao.update(tCarTypes);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<CarTypes> tCarTypesList) {
		carTypesDao.batchUpdate(tCarTypesList);
	}
}