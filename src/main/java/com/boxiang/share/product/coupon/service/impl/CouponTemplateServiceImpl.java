package com.boxiang.share.product.coupon.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.coupon.dao.CouponTemplateDao;
import com.boxiang.share.product.coupon.po.CouponTemplate;
import com.boxiang.share.product.coupon.service.CouponTemplateService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("couponTemplate")
public class CouponTemplateServiceImpl implements CouponTemplateService {
	@Resource(name="couponTemplateDao")
	private CouponTemplateDao couponTemplateDao;
	
	@Override
	public List<CouponTemplate> selectList(CouponTemplate couponTemplate) {
		return couponTemplateDao.selectList(couponTemplate);
	}

	@Override
	public Page<CouponTemplate> queryListPage(Page<CouponTemplate> page) {
	    page.setResultList(couponTemplateDao.queryListPage(page));
		return page;
	}
	
	@Override
	public CouponTemplate queryById(Integer id) {
		return couponTemplateDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(CouponTemplate couponTemplate) {
		couponTemplateDao.insert(couponTemplate);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(Integer id) {
		couponTemplateDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(CouponTemplate couponTemplate) {
		couponTemplateDao.update(couponTemplate);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<CouponTemplate> couponTemplateList) {
		couponTemplateDao.batchUpdate(couponTemplateList);
	}
}