package com.boxiang.share.product.carBrand.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.carBrand.dao.CarBrandDao;
import com.boxiang.share.product.carBrand.po.CarBrand;
import com.boxiang.share.product.carBrand.service.CarBrandService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("carBrand")
public class CarBrandServiceImpl implements CarBrandService {
	@Resource(name="carBrandDao")
	private CarBrandDao carBrandDao;
	
	@Override
	public List<CarBrand> selectList(CarBrand carBrand) {
		return carBrandDao.selectList(carBrand);
	}

	@Override
	public List<CarBrand> selectList2(CarBrand carBrand) {
		return carBrandDao.selectList2(carBrand);
	}

	@Override
	public Page<CarBrand> queryListPage(Page<CarBrand> page) {
	    page.setResultList(carBrandDao.queryListPage(page));
		return page;
	}
	
	@Override
	public CarBrand queryById(Integer id) {
		return carBrandDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(CarBrand carBrand) {
		carBrandDao.insert(carBrand);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(Integer id) {
		carBrandDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(CarBrand carBrand) {
		carBrandDao.update(carBrand);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<CarBrand> carBrandList) {
		carBrandDao.batchUpdate(carBrandList);
	}
}