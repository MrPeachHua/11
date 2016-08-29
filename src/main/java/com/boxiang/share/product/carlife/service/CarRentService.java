package com.boxiang.share.product.carlife.service;

import com.boxiang.share.product.carlife.po.CarRent;

import java.util.List;
import com.boxiang.framework.base.page.Page;

public interface CarRentService {

	List<CarRent> selectList(CarRent carRent);
	
	Page<CarRent> queryListPage(Page<CarRent> page);
	
	CarRent queryById(Integer id);
	
	void add(CarRent carRent);

	void delete(Integer id);
	
	void update(CarRent carRent);
	
	void batchUpdate(List<CarRent> carRentList);
}