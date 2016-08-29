package com.boxiang.share.product.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.boxiang.share.product.order.po.Monthlyparkinginfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.log.SystemLog;
import com.boxiang.share.product.order.dao.PropertyparkinginfoDao;
import com.boxiang.share.product.order.po.Propertyparkinginfo;
import com.boxiang.share.product.order.service.PropertyparkinginfoService;
import com.boxiang.share.utils.LogTypeEnum;

@DataSource(DataSourceEnum.MASTER)
@Service("propertyparkinginfo")
public class PropertyparkinginfoServiceImpl implements PropertyparkinginfoService {
	@Resource(name="propertyparkinginfoDao")
	private PropertyparkinginfoDao propertyparkinginfoDao;
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void updateParkingInfo(Propertyparkinginfo propertyparkinginfo){
		propertyparkinginfoDao.updateParkingInfo(propertyparkinginfo);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void updateParkingTime(Propertyparkinginfo propertyparkinginfo){
		propertyparkinginfoDao.updateParkingTime(propertyparkinginfo);
	}
	@Override
	public List<Propertyparkinginfo> selectList(Propertyparkinginfo propertyparkinginfo) {
		return propertyparkinginfoDao.selectList(propertyparkinginfo);
	}

	@Override
	public Page<Propertyparkinginfo> queryListPage(Page<Propertyparkinginfo> page) {
	    page.setResultList(propertyparkinginfoDao.queryListPage(page));
		return page;
	}
	
	@Override
	public Propertyparkinginfo queryById(java.lang.String id) {
		return propertyparkinginfoDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	@SystemLog(logType=LogTypeEnum.CAR_MANAGE,logSummary="添加产权车辆信息")
	public void add(Propertyparkinginfo propertyparkinginfo) {
		propertyparkinginfoDao.insert(propertyparkinginfo);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	@SystemLog(logType=LogTypeEnum.CAR_MANAGE,logSummary="删除产权车辆信息")
	public void delete(java.lang.String id) {
		propertyparkinginfoDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	@SystemLog(logType=LogTypeEnum.CAR_MANAGE,logSummary="修改产权车辆信息")
	public void update(Propertyparkinginfo propertyparkinginfo) {
		propertyparkinginfoDao.update(propertyparkinginfo);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<Propertyparkinginfo> propertyparkinginfoList) {
		propertyparkinginfoDao.batchUpdate(propertyparkinginfoList);
	}

@Override
@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
public void deleteByCarNumber(Propertyparkinginfo propertyparkinginfo) {
	propertyparkinginfoDao.deleteByCarNumber(propertyparkinginfo);
}

@Override
public List<Propertyparkinginfo> queryListExcel(
		Propertyparkinginfo propertyparkinginfo) {
	return propertyparkinginfoDao.queryListExcel(propertyparkinginfo);
}

@Override
public boolean saveList(
		List<List<Propertyparkinginfo>> propertyparkinginfoSecondList, int count) {
	boolean flag=false;
	try {
		
		for (List<Propertyparkinginfo> propertyparkinginfoList : propertyparkinginfoSecondList) {
			for (Propertyparkinginfo propertyparkinginfo : propertyparkinginfoList) {
				propertyparkinginfoDao.insert(propertyparkinginfo);
			}
		}if(count >  0){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		flag = true;
	} catch (Exception e) {
		flag = false;
	//	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	}
	return flag;
}
}