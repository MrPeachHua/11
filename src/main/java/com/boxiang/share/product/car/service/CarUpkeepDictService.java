package com.boxiang.share.product.car.service;

import com.boxiang.share.product.car.po.CarUpkeepDict;

import java.util.List;
import com.boxiang.framework.base.page.Page;

public interface CarUpkeepDictService {

	List<CarUpkeepDict> selectList(CarUpkeepDict carUpkeepDict);
	
	Page<CarUpkeepDict> queryListPage(Page<CarUpkeepDict> page);
	
	CarUpkeepDict queryById(String id);
	
	void add(CarUpkeepDict carUpkeepDict);

	void delete(String id);
	
	void update(CarUpkeepDict carUpkeepDict);
	
	void batchUpdate(List<CarUpkeepDict> carUpkeepDictList);
}