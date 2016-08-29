package com.boxiang.share.product.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.order.dao.MonthlyparkingfeeorderinfoDao;
import com.boxiang.share.product.order.po.Monthlyparkingfeeorderinfo;
import com.boxiang.share.product.order.service.MonthlyparkingfeeorderinfoService;

@DataSource(DataSourceEnum.MASTER)
@Service("monthlyparkingfeeorderinfo")
public class MonthlyparkingfeeorderinfoServiceImpl implements MonthlyparkingfeeorderinfoService {
	@Resource(name="monthlyparkingfeeorderinfoDao")
	private MonthlyparkingfeeorderinfoDao monthlyparkingfeeorderinfoDao;
	
	@Override
	public List<Monthlyparkingfeeorderinfo> selectList(Monthlyparkingfeeorderinfo monthlyparkingfeeorderinfo) {
		return monthlyparkingfeeorderinfoDao.selectList(monthlyparkingfeeorderinfo);
	}

	@Override
	public Page<Monthlyparkingfeeorderinfo> queryListPage(Page<Monthlyparkingfeeorderinfo> page) {
	    page.setResultList(monthlyparkingfeeorderinfoDao.queryListPage(page));
		return page;
	}
	
	@Override
	public Monthlyparkingfeeorderinfo queryById(java.lang.String id) {
		return monthlyparkingfeeorderinfoDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(Monthlyparkingfeeorderinfo monthlyparkingfeeorderinfo) {
		monthlyparkingfeeorderinfoDao.insert(monthlyparkingfeeorderinfo);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.String id) {
		monthlyparkingfeeorderinfoDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(Monthlyparkingfeeorderinfo monthlyparkingfeeorderinfo) {
		monthlyparkingfeeorderinfoDao.update(monthlyparkingfeeorderinfo);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<Monthlyparkingfeeorderinfo> monthlyparkingfeeorderinfoList) {
		monthlyparkingfeeorderinfoDao.batchUpdate(monthlyparkingfeeorderinfoList);
	}
}