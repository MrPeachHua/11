package com.boxiang.share.customer.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.customer.dao.CustomerGrowthDao;
import com.boxiang.share.customer.po.CustomerGrowth;
import com.boxiang.share.customer.service.CustomerGrowthService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("customerGrowth")
public class CustomerGrowthServiceImpl implements CustomerGrowthService {
	@Resource(name="customerGrowthDao")
	private CustomerGrowthDao customerGrowthDao;
	
	@Override
	public List<CustomerGrowth> selectList(CustomerGrowth customerGrowth) {
		return customerGrowthDao.selectList(customerGrowth);
	}

	@Override
	public Page<CustomerGrowth> queryListPage(Page<CustomerGrowth> page) {
	    page.setResultList(customerGrowthDao.queryListPage(page));
		return page;
	}

	@Override
	public Page<CustomerGrowth> queryListPage1(Page<CustomerGrowth> page) {
		page.setResultList(customerGrowthDao.queryListPage1(page));
		return page;
	}
	
	@Override
	public CustomerGrowth queryById(Integer id) {
		return customerGrowthDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(CustomerGrowth customerGrowth) {
		customerGrowthDao.insert(customerGrowth);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(Integer id) {
		customerGrowthDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(CustomerGrowth customerGrowth) {
		customerGrowthDao.update(customerGrowth);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<CustomerGrowth> customerGrowthList) {
		customerGrowthDao.batchUpdate(customerGrowthList);
	}
}