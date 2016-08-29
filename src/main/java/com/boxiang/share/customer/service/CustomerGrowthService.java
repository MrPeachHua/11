package com.boxiang.share.customer.service;

import com.boxiang.share.customer.po.CustomerGrowth;

import java.util.List;
import com.boxiang.framework.base.page.Page;

public interface CustomerGrowthService {

	List<CustomerGrowth> selectList(CustomerGrowth customerGrowth);
	
	Page<CustomerGrowth> queryListPage(Page<CustomerGrowth> page);

	Page<CustomerGrowth> queryListPage1(Page<CustomerGrowth> page);
	
	CustomerGrowth queryById(Integer id);
	
	void add(CustomerGrowth customerGrowth);

	void delete(Integer id);
	
	void update(CustomerGrowth customerGrowth);
	
	void batchUpdate(List<CustomerGrowth> customerGrowthList);

}