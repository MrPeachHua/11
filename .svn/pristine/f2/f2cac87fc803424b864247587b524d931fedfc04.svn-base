package com.boxiang.share.product.qrcode.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.boxiang.share.product.qrcode.dao.CarlovQrcodeDao;
import com.boxiang.share.product.qrcode.po.CarlovQrcode;
import com.boxiang.share.product.qrcode.service.CarlovQrcodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("carlovQrcodeService")
public class CarlovQrcodeServiceImpl implements CarlovQrcodeService {
	@Resource(name="carlovQrcodeDao")
	private CarlovQrcodeDao carlovQrcodeDao;
	
	@Override
	public List<CarlovQrcode> selectList(CarlovQrcode tCarlovQrcode) {
		return carlovQrcodeDao.selectList(tCarlovQrcode);
	}

	@Override
	public Page<CarlovQrcode> queryListPage(Page<CarlovQrcode> page) {
	    page.setResultList(carlovQrcodeDao.queryListPage(page));
		return page;
	}
	
	@Override
	public CarlovQrcode queryById(String id) {
		return carlovQrcodeDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(CarlovQrcode tCarlovQrcode) {
		carlovQrcodeDao.insert(tCarlovQrcode);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(String id) {
		carlovQrcodeDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(CarlovQrcode tCarlovQrcode) {
		carlovQrcodeDao.update(tCarlovQrcode);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<CarlovQrcode> tCarlovQrcodeList) {
		carlovQrcodeDao.batchUpdate(tCarlovQrcodeList);
	}

	@Override
	public Page<CarlovQrcode> queryListPageForQrcode(Page<CarlovQrcode> page) {
		page.setResultList(carlovQrcodeDao.queryListPageForQrcode(page));
		return (page);
	}

	@Override
	public List<CarlovQrcode> selectDayData(Map<String, Object> param) {
		return carlovQrcodeDao.selectDayData(param);
	}
}