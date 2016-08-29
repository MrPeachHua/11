package com.boxiang.share.product.car.service;

import com.boxiang.share.product.car.po.CarMain;

import java.util.List;
import com.boxiang.framework.base.page.Page;

public interface CarMainService {

	List<CarMain> selectList(CarMain carMain);
	
	Page<CarMain> queryListPage(Page<CarMain> page);
	
	CarMain queryById(Integer id);
	
	void add(CarMain carMain);

	void delete(Integer id);
	
	void update(CarMain carMain);
	
	void batchUpdate(List<CarMain> carMainList);
}