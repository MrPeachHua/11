package com.boxiang.share.product.ccic.service;

import com.boxiang.share.product.ccic.bo.CarMessageSync;
import com.boxiang.share.product.ccic.bo.CarResponse;
import com.boxiang.share.product.ccic.po.CcicCustomer;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;

public interface CcicCustomerService {

	List<CcicCustomer> selectList(CcicCustomer ccicCustomer);
	
	Page<CcicCustomer> queryListPage(Page<CcicCustomer> page);
	
	CcicCustomer queryById(Integer id);
	
	void add(CcicCustomer ccicCustomer);

	void delete(Integer id);
	
	void update(CcicCustomer ccicCustomer);
	
	void batchUpdate(List<CcicCustomer> ccicCustomerList);

	String saveInfo(Map map);
	
	CarResponse carInfoSync(CarMessageSync msg);
}