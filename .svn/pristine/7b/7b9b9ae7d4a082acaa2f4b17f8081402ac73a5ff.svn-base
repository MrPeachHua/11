package com.boxiang.share.product.advertising.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.advertising.dao.AdvertisingDao;
import com.boxiang.share.product.advertising.po.Advertising;
import com.boxiang.share.product.advertising.service.AdvertisingService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("advertising")
public class AdvertisingServiceImpl implements AdvertisingService {
	@Resource(name="advertisingDao")
	private AdvertisingDao advertisingDao;
	
	@Override
	public List<Advertising> selectList(Advertising advertising) {
		return advertisingDao.selectList(advertising);
	}

	@Override
	public Page<Advertising> queryListPage(Page<Advertising> page) {
	    page.setResultList(advertisingDao.queryListPage(page));
		return page;
	}
	
	@Override
	public Advertising queryById(java.lang.Integer id) {
		return advertisingDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(Advertising advertising) {
		advertisingDao.insert(advertising);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.Integer id) {
		advertisingDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(Advertising advertising) {
		advertisingDao.update(advertising);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<Advertising> advertisingList) {
		advertisingDao.batchUpdate(advertisingList);
	}

@Override
public List<Advertising> selectAdvertising(Advertising advertising) {
	return advertisingDao.selectAdvertising(advertising);
}


}