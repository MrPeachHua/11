package com.boxiang.share.product.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.order.dao.CenuseDao;
import com.boxiang.share.product.order.po.Cenuse;
import com.boxiang.share.product.order.service.CenuseService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("cenuse")
public class CenuseServiceImpl implements CenuseService {
	@Resource(name="cenuseDao")
	private CenuseDao cenuseDao;
	
	@Override
	public List<Cenuse> selectList(Cenuse cenuse) {
		return cenuseDao.selectList(cenuse);
	}

	@Override
	public Page<Cenuse> queryListPage(Page<Cenuse> page) {
	    page.setResultList(cenuseDao.queryListPage(page));
		return page;
	}
	
	@Override
	public Cenuse queryById(Integer id) {
		return cenuseDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(Cenuse cenuse) {
		cenuseDao.insert(cenuse);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(Integer id) {
		cenuseDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(Cenuse cenuse) {
		cenuseDao.update(cenuse);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<Cenuse> cenuseList) {
		cenuseDao.batchUpdate(cenuseList);
	}
}