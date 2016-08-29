package com.boxiang.share.product.carBrand.service;

import com.boxiang.share.product.carBrand.po.CarBrand;

import java.util.List;
import com.boxiang.framework.base.page.Page;

public interface CarBrandService {

	List<CarBrand> selectList(CarBrand carBrand);
	List<CarBrand> selectList2(CarBrand carBrand);
	
	Page<CarBrand> queryListPage(Page<CarBrand> page);
	
	CarBrand queryById(Integer id);
	
	void add(CarBrand carBrand);

	void delete(Integer id);
	
	void update(CarBrand carBrand);
	
	void batchUpdate(List<CarBrand> carBrandList);
}