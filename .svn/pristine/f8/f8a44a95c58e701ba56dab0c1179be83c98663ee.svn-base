package com.boxiang.share.product.car.service;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.car.po.CarUpkeep;

import java.util.List;
import java.util.Map;

public interface CarUpkeepService {

	List<CarUpkeep> selectList(CarUpkeep carUpkeep);
	
	Page<CarUpkeep> queryListPage(Page<CarUpkeep> page);
	
	CarUpkeep queryById(String id);
	
	void add(CarUpkeep carUpkeep);

	void delete(String id);
	
	void update(CarUpkeep carUpkeep);
	
	void batchUpdate(List<CarUpkeep> carUpkeepList);

	List<Map<String,Object>> gainupkeep(Map<String, Object> params);
}