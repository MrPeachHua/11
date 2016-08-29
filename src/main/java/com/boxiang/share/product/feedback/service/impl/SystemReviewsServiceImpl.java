package com.boxiang.share.product.feedback.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.feedback.dao.SystemReviewsDao;
import com.boxiang.share.product.feedback.po.SystemReviews;
import com.boxiang.share.product.feedback.service.SystemReviewsService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("systemReviews")
public class SystemReviewsServiceImpl implements SystemReviewsService {
	@Resource(name="systemReviewsDao")
	private SystemReviewsDao systemReviewsDao;
	
	@Override
	public List<SystemReviews> selectList(SystemReviews systemReviews) {
		return systemReviewsDao.selectList(systemReviews);
	}

	@Override
	public Page<SystemReviews> queryListPage(Page<SystemReviews> page) {
	    page.setResultList(systemReviewsDao.queryListPage(page));
		return page;
	}
	
	@Override
	public SystemReviews queryById(java.lang.String id) {
		return systemReviewsDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(SystemReviews systemReviews) {
		systemReviewsDao.insert(systemReviews);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.String id) {
		systemReviewsDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(SystemReviews systemReviews) {
		systemReviewsDao.update(systemReviews);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<SystemReviews> systemReviewsList) {
		systemReviewsDao.batchUpdate(systemReviewsList);
	}
}