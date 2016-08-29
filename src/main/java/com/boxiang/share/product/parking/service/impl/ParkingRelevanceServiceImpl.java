package com.boxiang.share.product.parking.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.parking.dao.ParkingRelevanceDao;
import com.boxiang.share.product.parking.po.ParkingRelevance;
import com.boxiang.share.product.parking.service.ParkingRelevanceService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("parkingRelevance")
public class ParkingRelevanceServiceImpl implements ParkingRelevanceService {
	@Resource(name="parkingRelevanceDao")
	private ParkingRelevanceDao parkingRelevanceDao;
	
	@Override
	public List<ParkingRelevance> selectList(ParkingRelevance parkingRelevance) {
		return parkingRelevanceDao.selectList(parkingRelevance);
	}

	@Override
	public Page<ParkingRelevance> queryListPage(Page<ParkingRelevance> page) {
	    page.setResultList(parkingRelevanceDao.queryListPage(page));
		return page;
	}
	
	@Override
	public ParkingRelevance queryById(java.lang.Integer id) {
		return parkingRelevanceDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(ParkingRelevance parkingRelevance) {
		parkingRelevanceDao.insert(parkingRelevance);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.Integer id) {
		parkingRelevanceDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(ParkingRelevance parkingRelevance) {
		parkingRelevanceDao.update(parkingRelevance);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<ParkingRelevance> parkingRelevanceList) {
		parkingRelevanceDao.batchUpdate(parkingRelevanceList);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public int del(String parkingId) {
		return parkingRelevanceDao.del(parkingId);
	}
}