package com.boxiang.share.product.order.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.log.SystemLog;
import com.boxiang.share.product.order.dao.MonthlyparkinginfoDao;
import com.boxiang.share.product.order.po.Monthlyparkinginfo;
import com.boxiang.share.product.order.service.MonthlyparkinginfoService;
import com.boxiang.share.utils.LogTypeEnum;

@DataSource(DataSourceEnum.MASTER)
@Service("monthlyparkinginfo")
public class MonthlyparkinginfoServiceImpl implements MonthlyparkinginfoService {
	@Resource(name="monthlyparkinginfoDao")
	private MonthlyparkinginfoDao monthlyparkinginfoDao;
	
	@Override
	public List<Monthlyparkinginfo> selectList(Monthlyparkinginfo monthlyparkinginfo) {
		return monthlyparkinginfoDao.selectList(monthlyparkinginfo);
	}
@Override
@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
public void updateParkingInfo(Monthlyparkinginfo monthlyparkinginfo){
	monthlyparkinginfoDao.updateParkingInfo(monthlyparkinginfo);
}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void updateParkingTime(Monthlyparkinginfo monthlyparkinginfo){
		monthlyparkinginfoDao.updateParkingTime(monthlyparkinginfo);
	}
	@Override
	public Page<Monthlyparkinginfo> queryListPage(Page<Monthlyparkinginfo> page) {
	    page.setResultList(monthlyparkinginfoDao.queryListPage(page));
		return page;
	}
	
	@Override
	public Monthlyparkinginfo queryById(java.lang.String id) {
		return monthlyparkinginfoDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	@SystemLog(logType=LogTypeEnum.CAR_MANAGE,logSummary="添加月租车辆信息")
	public void add(Monthlyparkinginfo monthlyparkinginfo) {
		monthlyparkinginfoDao.insert(monthlyparkinginfo);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	@SystemLog(logType=LogTypeEnum.CAR_MANAGE,logSummary="删除月租车辆信息")
	public void delete(java.lang.String id) {
		monthlyparkinginfoDao.delete(id);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void deleteByCarnumberAndId(Monthlyparkinginfo monthlyparkinginfo) {
		monthlyparkinginfoDao.deleteByCarnumberAndId(monthlyparkinginfo);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	@SystemLog(logType=LogTypeEnum.CAR_MANAGE,logSummary="修改月租车辆信息")
	public void update(Monthlyparkinginfo monthlyparkinginfo) {
		monthlyparkinginfoDao.update(monthlyparkinginfo);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<Monthlyparkinginfo> monthlyparkinginfoList) {
		monthlyparkinginfoDao.batchUpdate(monthlyparkinginfoList);
	}

@Override
public List<Monthlyparkinginfo> queryListExcel(
		Monthlyparkinginfo monthlyparkinginfo) {
	return monthlyparkinginfoDao.queryListExcel(monthlyparkinginfo);
}

@Override
public boolean saveList(List<List<Monthlyparkinginfo>> monthlyParkingInfoSecondList,
		int count) {
	boolean flag=false;
	try {
		
		for (List<Monthlyparkinginfo> monthlyParkingInfoList : monthlyParkingInfoSecondList) {
			for (Monthlyparkinginfo monthlyParkingInfo : monthlyParkingInfoList) {
				monthlyparkinginfoDao.insert(monthlyParkingInfo);
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

	@Override
	public List<Map<String, Object>> getMonthlyEquity(Object obj) {
		return monthlyparkinginfoDao.getMonthlyEquity(obj);
	}
}